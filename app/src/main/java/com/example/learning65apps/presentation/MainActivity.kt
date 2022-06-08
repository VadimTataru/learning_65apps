package com.example.learning65apps.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learning65apps.ICommunicator
import com.example.learning65apps.R
import com.example.learning65apps.databinding.ActivityMainBinding
import com.example.learning65apps.presentation.fragments.ContactDetailsFragment
import com.example.learning65apps.presentation.fragments.ContactListFragment

class MainActivity : AppCompatActivity(), ICommunicator {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.flContainer,
                    ContactListFragment(),
                    ContactListFragment.TAG
                )
                .commit()
        }

    }

    override fun passData(data: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flContainer,
                ContactDetailsFragment.newInstance(data),
                ContactDetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}