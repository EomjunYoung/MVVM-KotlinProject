package com.example.mykotlin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao{

    @Query("Select * from contact order by name asc")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}