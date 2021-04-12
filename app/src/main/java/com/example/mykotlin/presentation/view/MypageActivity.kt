package com.example.mykotlin.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mykotlin.R
import com.example.mykotlin.data.model.Contact
import com.example.mykotlin.data.model.User
import com.example.mykotlin.databinding.ActivityMypageBinding
import com.example.mykotlin.presentation.viewModel.ContactViewModel
import com.example.mykotlin.presentation.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : AppCompatActivity(){

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        //DataBinding을 이용하게되면, xml명을 기반으로 뒤에 +Binding명칭이 붙은 객체가 하나 만들어짐
        //이를 통해 DataBinding된 xml을 inflate하고, id를 불러올 수 있다.
        var binding: ActivityMypageBinding = DataBindingUtil.setContentView(this, R.layout.activity_mypage)

//        textview 등 component의 id는 카멜표기법으로 자동생성된다.
//        binding.tvPatientName.text = "엄준영" (component id를 tv_patientName을 만들면 tv_PatientName으로 만들어짐)

//
//        userViewModel.getAll().observe(this, Observer<User> { userObserver ->
//
//            binding.user = User(userObserver.patientName, userObserver.gender, 123,
//                "진료과","진료의","병동","침대번호", "방번호")
//
//            userViewModel.getAll().observe(this, Observer<User> { userObserver ->
//
//                binding.user = User(userObserver.patientName, userObserver.gender, 123,
//                    "진료과","진료의","병동","침대번호", "방번호")
//
//            })
//
//        })
        userViewModel.getAll().observe(this, Observer<User> {

//            binding.user?.patientName = "51"

            if(it!=null)
           Log.d("eom", it.patientName)
            else
                Log.d("eom", "test")
            //binding.tvPatientName.text = it.patientName

        })




        var txtGender =""

        binding.radioGroup.setOnCheckedChangeListener{
                radioGroup, checkedId ->
            when(checkedId){
                R.id.btn_man -> {txtGender = "남"}
                R.id.btn_woman -> {txtGender = "여"}
            }
        }


        binding.btnSave.setOnClickListener{

            var user = User(binding.etPatientName.text.toString(), txtGender, binding.etPatientId.text.toString().toLong(),
            binding.etDepartmentName.text.toString(), binding.etPhysicianName.text.toString(), binding.etWardName.text.toString(), binding.etBedName.text.toString(),
            binding.etRoomName.text.toString())
//
//            binding.user = User(binding.etPatientName.text.toString(), txtGender, binding.etPatientId.text.toString().toLong(),
//                binding.etDepartmentName.text.toString(), binding.etPhysicianName.text.toString(), binding.etWardName.text.toString(), binding.etBedName.text.toString(),
//                binding.etRoomName.text.toString())

            userViewModel.update(user)



        }





    }
}