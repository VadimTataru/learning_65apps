package com.example.learning65apps.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.learning65apps.R
import com.example.learning65apps.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {
    lateinit var binding: FragmentContactDetailsBinding
    private var contactId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactId = arguments?.getString(BUNDLE_KEY)
        requireActivity().title = getString(R.string.contact_details_title)
    }

    companion object {
        private const val BUNDLE_KEY = "data"
        const val TAG = "contactDetailsFragment"
        fun newInstance(data: String) = ContactDetailsFragment().apply {
            arguments = bundleOf(BUNDLE_KEY to data)
        }
    }
}