package com.example.learning65apps.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learning65apps.ICommunicator
import com.example.learning65apps.R
import com.example.learning65apps.databinding.FragmentContactListBinding
import com.example.learning65apps.domain.models.ContactModel
import com.example.learning65apps.domain.services.DataService

class ContactListFragment : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    private var communicator: ICommunicator? = null
    private var dataService: DataService.IDataService? = null

    private val callback = object : IContactList {
        override fun getContactArrayList(list: List<ContactModel>) {
            requireView().post {
                with(binding) {
                    imgContactFirst.setImageResource(list[0].contactPhoto)
                    tvContactName.text = list[0].contactName
                    tvContactPhone.text = list[0].firstPhone
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ICommunicator) {
            communicator = context
        }

        if(context is DataService.IDataService) {
            dataService = context
        }
        Log.d("tag", "onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater)
        Log.d("tag", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.contact_list_title)

        binding.contactDataCardview.setOnClickListener {
            communicator?.passData(0)
        }
        dataService?.getService()?.getContactList(callback)
        Log.d("tag", "onViewCreated")
    }

    override fun onDetach() {
        communicator = null
        dataService = null
        super.onDetach()
        Log.d("tag", "onDetach")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        Log.d("tag", "onDestroyView")
    }

    interface IContactList {
        fun getContactArrayList(list: List<ContactModel>)
    }

    companion object {
        const val TAG = "contactListFragment"
    }
}