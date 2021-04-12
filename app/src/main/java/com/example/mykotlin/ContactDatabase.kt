package com.example.mykotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mykotlin.data.model.Contact
import com.example.mykotlin.data.model.User

/**
 * RoomDatabase를 상속하는 추상클래스이며, 테이블과
 * 버전을 정의하는 곳이다.
 **/
@Database(entities = [Contact::class, User::class], version = 2)
abstract class ContactDatabase: RoomDatabase()
{

    //Dao를 추상메소드로 추가하는 공간
    abstract fun contactDao(): ContactDao
    abstract fun userDao(): UserDao

    //static이 없는 kotlin은 companion을 붙여 singleton pattern을 사용할 수 있다
    //또한 kotlin의 object타입으로 만들어진 class는 프로그램 전체에서 공유할 수 있는
    //하나뿐인 객체이다, 아래는 DB에 접근하기 위한 INSTANCE이므로 하나만 있어도 됨
    //DB에 대한 이름이고, db명으로 각 DB를 구분함
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