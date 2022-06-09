package com.example.learning65apps.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.learning65apps.R
import com.example.learning65apps.databinding.FragmentContactDetailsBinding
import com.example.learning65apps.domain.models.ContactModel
import com.example.learning65apps.domain.services.DataService

class ContactDetailsFragment : Fragment() {
    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var contactId: Int? = null
    private var dataService: DataService.IDataService? = null
    private val callback = object : IContactDetails {
        override fun getContactDetails(contact: ContactModel) {
            requireView().post {
                with(binding) {
                    imgContactSecond.setImageResource(contact.contactPhoto)
                    tvContactName.text = contact.contactName
                    etPhoneFirst.text = contact.firstPhone
                    etPhoneSecond.text = contact.secondPhone
                    etEmailFirst.text = contact.firstEmail
                    etEmailSecond.text = contact.secondEmail
                    etDescription.text = contact.contactDescription
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DataService.IDataService) {
            dataService = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactId = arguments?.getInt(BUNDLE_KEY)
        requireActivity().title = getString(R.string.contact_details_title)
        contactId?.let {
            dataService?.getService()?.getContactDetails(callback, it)
        }
    }

    override fun onDetach() {
        super.onDetach()
        dataService = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface IContactDetails {
        fun getContactDetails(contact: ContactModel)
    }

    companion object {
        private const val BUNDLE_KEY = "data"
        const val TAG = "contactDetailsFragment"
        fun newInstance(data: Int) = ContactDetailsFragment().apply {
            arguments = bundleOf(BUNDLE_KEY to data)
        }
    }
}