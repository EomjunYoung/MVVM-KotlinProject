package com.example.mykotlin

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mykotlin.data.model.Contact

/**
 * Database Access Object, 데이터베이스에 실질적으로 접근해서
 * CURD를 시행한다. MVVM패턴에서의 ViewModel은 DB에 직접접근해서
 * 데이터를 불러오지않고, Repository와 Room 등(AAC)을 이용한다.
 * **/
@Dao
interface ContactDao{

    @Query("Select * from contact order by name asc")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}