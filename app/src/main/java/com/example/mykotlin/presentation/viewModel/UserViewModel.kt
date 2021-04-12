package com.example.mykotlin.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mykotlin.data.model.User
import com.example.mykotlin.data.repository.UserRepository

class UserViewModel(application: Application): AndroidViewModel(application){

    private val repository =
            UserRepository(application)
    private val users = repository.getAll()

    //LiveData를 통해 데이터가 갱신되면 UI에서 확인이 가능하고
    //이를 가능케 하는것이 .observe 메소드임
    fun getAll(): LiveData<User>{
        return this.users
    }
//
//    fun updateMypage(user: User){
//        repository.updateMypage(user)
//    }

    fun updateMypage2(patientName: String, gender: String, patientId: Long, departmentName: String,
                      physicianName: String, wardName: String, bedName: String, roomName: String){
        repository.updateMypage2(patientName, gender, patientId, departmentName, physicianName,
        wardName, bedName, roomName)
    }

    fun insert(user: User){
        repository.insert(user)
    }

    fun delete(user: User){
        repository.delete(user)
    }

    fun update(user: User){
        repository.update(user)
    }

}
