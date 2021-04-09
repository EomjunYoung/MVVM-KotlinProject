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

        //DataBinding을 이용하게되면, xml명을 기반으로 뒤에 +Binding명칭이 붙은 객체가 하나 만들어짐
        //이를 통해 DataBinding된 xml을 inflate하고, id를 불러올 수 있다.
        var binding: ActivityMypageBinding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)

        //textview 등 component의 id는 카멜표기법으로 자동생성된다.
        binding.tvPatientName.text = "엄준영"
//        binding.tvAge.text = "30"
        binding.etPatientName

        binding.user = User("eom", "30")


//        tv_name.setText("엄준영")
//        tv_age.setText("302")


    }
}