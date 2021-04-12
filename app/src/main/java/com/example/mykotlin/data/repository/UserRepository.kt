package com.example.mykotlin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mykotlin.UserDao
import com.example.mykotlin.ContactDatabase
import com.example.mykotlin.data.model.User
import java.lang.Exception

class UserRepository(application: Application){


    private val contactDatabase = ContactDatabase.getInstance(
            application
    )!!
    private val userDao: UserDao = contactDatabase.userDao()
    private val users: LiveData<User> = userDao.getAll()

    fun getAll(): LiveData<User>{
        return users
    }


    fun insert(user: User){
        try{
            val thread = Thread(Runnable { userDao.insert(user) })
            thread.start()
        } catch(e: Exception){}
    }

    fun delete(user: User){
        try{
            val thread = Thread(Runnable { userDao.delete(user) })
            thread.start()
        } catch(e: Exception){}
    }

    fun update(user: User){
        try{
            val thread = Thread(Runnable { userDao.update(user) })
            thread.start()
        } catch(e: Exception){}
    }
//
//    fun updateMypage(user: User){
//        try{
//            val thread = Thread(Runnable { userDao.updateMyPage(user) })
//            thread.start()
//        } catch(e: Exception){}
//    }

    fun updateMypage2(patientName: String, gender: String, patientId: Long, departmentName: String,
                      physicianName: String, wardName: String, bedName: String, roomName: String){
        try{
            val thread = Thread(Runnable { userDao.updateMyPage2(patientName, gender, patientId, departmentName, physicianName,
                    wardName, bedName, roomName) })
            thread.start()
        } catch(e: Exception){}
    }
}