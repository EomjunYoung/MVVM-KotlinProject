package com.example.mykotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase: RoomDatabase()
{

    abstract fun contactDao(): ContactDao

    //static이 없는 kotlin은 companion을 붙여 singleton pattern을 사용할 수 있다
    //또한 kotlin의 object타입으로 만들어진 class는 프로그램 전체에서 공유할 수 있는
    //하나뿐인 객체이다
    companion object{
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase?{
            if(INSTANCE == null){
                synchronized(ContactDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ContactDatabase::class.java, "contact").fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}