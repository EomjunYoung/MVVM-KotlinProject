package com.example.mykotlin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mykotlin.ContactDao
import com.example.mykotlin.ContactDatabase
import com.example.mykotlin.data.model.Contact
import java.lang.Exception

class ContactRepository(application: Application){


    //Repository는 DB에서 데이터를 가져오는 것을 수행하기 위한 MODEL로써,
    //DAO를 통해서 실제 DB에 접근하고, 관련행위를 수행하는 로직이 담겨있다.
    //또 이를 통해서 LiveData를 받아오고, 이를 ViewModel과 View에 전달하여 항상 최신데이터를 유지한다.
    private val contactDatabase = ContactDatabase.getInstance(
        application
    )!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>>{
        return contacts
    }

    fun insert(contact: Contact){
        try{
            val thread = Thread(Runnable { contactDao.insert(contact) })
            thread.start()
        } catch(e: Exception){}
    }

    fun delete(contact: Contact){
        try{
            val thread = Thread(Runnable { contactDao.delete(contact) })
            thread.start()
        } catch(e: Exception){}
    }

}