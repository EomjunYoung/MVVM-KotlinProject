package com.example.mykotlin

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mykotlin.data.model.User

/**
 * Database Access Object, 데이터베이스에 실질적으로 접근해서
 * CURD를 시행한다. MVVM패턴에서의 ViewModel은 DB에 직접접근해서
 * 데이터를 불러오지않고, Repository와 Room 등(AAC)을 이용한다.
 * **/
@Dao
interface UserDao{

    @Query("Select * from user")
    fun getAll(): LiveData<User>

//
//
//    @Query("Update user set patientName=user.patientName, gender=user.gender, patientId=user.patientId, departmentName=user.departmentName, physicianName=user.physicianName, wardName=user.wardName, bedName=user.bedName, roomName=user.roomName")
//    fun updateMyPage(user: User)

    @Query("Update user set patientName=:patientName, gender=:gender, patientId=:patientId, departmentName=:departmentName, physicianName=:physicianName, wardName=:wardName, bedName=:bedName, roomName=:roomName")
    fun updateMyPage2(patientName: String, gender: String, patientId: Long, departmentName: String,
                        physicianName: String, wardName: String, bedName: String, roomName: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)

}