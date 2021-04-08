package com.example.mykotlin.presentation.view

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.example.mykotlin.presentation.viewModel.ContactViewModel
import com.example.mykotlin.R
import com.example.mykotlin.data.model.Contact
import com.google.firebase.FirebaseApp
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_add.*
import java.nio.file.Files.find
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class AddActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    private var fbStorage : FirebaseStorage? = null
    private var pickImageFromAlbum = 0
    private var uriPhoto : Uri? = null
    private var viewProfile : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//뒤로가기 버튼추가
        supportActionBar?.title = "아이템 추가하기" //툴바 타이틀 설정

        FirebaseApp.initializeApp(this)
        fbStorage = FirebaseStorage.getInstance()

        xml_frg_prf_btn_upload.setOnClickListener{
            //open album
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum)
        }



        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        //intent null check & get extras
        //RecyclerView item을 한번클릭하면 값이 넘어오면서 수정할 수 있게하기 위한 code
        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(
                EXTRA_CONTACT_NUMBER
            )
            && intent.hasExtra(EXTRA_CONTACT_ID)) {

            add_edittext_name.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            add_edittext_number.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        add_button.setOnClickListener {
            val name = add_edittext_name.text.toString().trim()
            val number = add_edittext_number.text.toString()

            if (name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "Please enter name and number.", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].toUpperCase()
                val contact = Contact(
                    id,
                    name,
                    number,
                    initial
                )
                contactViewModel.insert(contact)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }

    //toolbar의
    override fun onOptionsItemSelected(item: MenuItem):Boolean {

        when(item?.itemId) {

            android.R.id.home -> {
                finish()
                return super.onOptionsItemSelected(item)
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == pickImageFromAlbum){
            if(resultCode == Activity.RESULT_OK){
                uriPhoto = data?.data
                xml_frg_prf_img_profile.setImageURI(uriPhoto)

                if(ContextCompat.checkSelfPermission(viewProfile!!.context,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    funImageUpload(viewProfile!!)
                }
            }
        }
    }

    private fun funImageUpload(view: View){
        var timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        var imgFileName = "IMAGE_" + timeStamp + "_.png"
        var storageRef = fbStorage?.reference?.child("Image")?.child(imgFileName)

        storageRef?.putFile(uriPhoto!!)?.addOnSuccessListener {
            Toast.makeText(view.context, "Image Uploaded", Toast.LENGTH_LONG).show()
        }
    }
}

