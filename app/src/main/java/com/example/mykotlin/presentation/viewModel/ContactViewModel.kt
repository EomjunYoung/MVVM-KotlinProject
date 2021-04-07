package com.example.mykotlin.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mykotlin.data.model.Contact
import com.example.mykotlin.data.repository.ContactRepository

class ContactViewModel(application: Application): AndroidViewModel(application){

    private val repository =
        ContactRepository(application)
    private val contacts = repository.getAll()

    //LiveData를 통해 데이터가 갱신되면 UI에서 확인이 가능하고
    //이를 가능케 하는것이 .observe 메소드임
    fun getAll(): LiveData<List<Contact>>{
        return this.contacts
    }

    fun insert(contact: Contact){
        repository.insert(contact)
    }

    fun delete(contact: Contact){
        repository.delete(contact)
    }
}