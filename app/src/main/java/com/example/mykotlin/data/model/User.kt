package com.example.mykotlin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DataBase안에 있는 테이블을 Kotlin(or Java) 클래스로 나타낸 것이다.
 * 데이터 모델 클래스라고 볼 수 있다.
 * **/
@Entity(tableName = "user")
data class User(

    @ColumnInfo(name = "patientName")
    var patientName: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @PrimaryKey(autoGenerate = true)
    var patientId: Long?,

    @ColumnInfo(name="departmentName")
    var departmentName: String,

    @ColumnInfo(name="physicianName")
    var physicianName: String,

    @ColumnInfo(name="wardName")
    var wardName: String,

    @ColumnInfo(name="bedName")
    var bedName: String,

    @ColumnInfo(name="roomName")
    var roomName: String

){constructor(): this("", "", null, "",
"","","","")}
