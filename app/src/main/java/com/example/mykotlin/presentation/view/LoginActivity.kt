package com.example.mykotlin.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlin.R
import com.example.mykotlin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    //databinding을 사용하여 객체의 binding클래스를 만들려면 해당 xml은 layout을 사용해야한다.
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            // Validate...
            loginUser(email, password)

        }
    }

    private fun loginUser(email: String, password: String){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){

            if(it.isSuccessful){
                Log.d("eeom", "로그인에 성공하였습니다!")

            } else {

                Log.d("eeom", "로그인에 실패하였습니다!")
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            binding.txtResult.text = "Email: ${user.email}\nUid: ${user.uid}"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}