package com.example.mykotlin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DataBase안에 있는 테이블을 Kotlin(or Java) 클래스로 나타낸 것이다.
 * 데이터 모델 클래스라고 볼 수 있다.
 * **/
@Entity(tableName = "contact")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "initial")
    var initial: Char

){constructor(): this(null, "","", '\u0000')}