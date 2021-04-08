package com.example.mykotlin.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mykotlin.R
import com.example.mykotlin.data.model.User
import com.example.mykotlin.databinding.ActivityMypageBinding
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_mypage)

        var binding: ActivityMypageBinding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)

        binding.tvName.text = "엄준영"
        binding.tvAge.text = "30"

        binding.user = User("eom", "30")


//        tv_name.setText("엄준영")
//        tv_age.setText("302")


    }
}