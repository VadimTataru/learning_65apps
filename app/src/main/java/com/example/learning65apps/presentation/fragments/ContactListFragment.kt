package com.example.learning65apps.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learning65apps.ICommunicator
import com.example.learning65apps.R
import com.example.learning65apps.databinding.FragmentContactListBinding

class ContactListFragment : Fragment() {

    lateinit var binding: FragmentContactListBinding
    private var communicator: ICommunicator? = null
    private var contactName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            contactName = getString()
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater)

        communicator = activity as ICommunicator

        binding.contactDataCardview.setOnClickListener {
            communicator?.passData("data")
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.contact_list_title)
    }

    companion object {
        fun newInstance() = ContactListFragment()
        const val TAG = "contactListFragment"
    }
}