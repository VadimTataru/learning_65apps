package com.example.learning65apps.domain.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.learning65apps.R
import com.example.learning65apps.domain.models.ContactModel
import com.example.learning65apps.presentation.fragments.ContactDetailsFragment
import com.example.learning65apps.presentation.fragments.ContactListFragment
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class DataService: Service() {

    private val binder = DataBinder()
    private val contactList = listOf(
        ContactModel(
            0,
            "V",
            "89511998595",
            "89127687664",
            "tavadstep@gmail.com",
            "tataruvad@yandex.ru",
            "ацтань я кушаю",
            R.drawable.ic_launcher_foreground
        )
    )

    fun getContactList(callback: ContactListFragment.IContactList) {
        val weakReferenceCallback = WeakReference(callback)
        thread {
            Thread.sleep(3000)
            weakReferenceCallback.get()?.getContactArrayList(contactList)
        }
    }

    fun getContactDetails(callback: ContactDetailsFragment.IContactDetails, contactId: Int) {
        val weakReferenceCallback = WeakReference(callback)
        thread {
            Thread.sleep(3000)
            weakReferenceCallback.get()?.getContactDetails(contactList[contactId])
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    interface IDataService {
        fun getService(): DataService?
    }

    inner class DataBinder: Binder() {
        fun getService() = this@DataService
    }
}