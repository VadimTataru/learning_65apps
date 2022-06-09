package com.example.learning65apps.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.learning65apps.ICommunicator
import com.example.learning65apps.R
import com.example.learning65apps.databinding.ActivityMainBinding
import com.example.learning65apps.domain.services.DataService
import com.example.learning65apps.presentation.fragments.ContactDetailsFragment
import com.example.learning65apps.presentation.fragments.ContactListFragment

class MainActivity : AppCompatActivity(), ICommunicator, DataService.IDataService {

    private var binding: ActivityMainBinding? = null
    private var dataService: DataService? = null
    private var isServiceBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as DataService.DataBinder
            dataService = binder.getService()
            isServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            dataService = null
            isServiceBound = false
        }
    }

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
        initService()
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isServiceBound = false
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun getService() = dataService

    override fun passData(id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flContainer,
                ContactDetailsFragment.newInstance(id),
                ContactDetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initService() {
        val intent = Intent(this, DataService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }
}
