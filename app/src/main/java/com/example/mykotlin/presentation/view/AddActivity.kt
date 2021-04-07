package com.example.mykotlin.presentation.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mykotlin.presentation.viewModel.ContactViewModel
import com.example.mykotlin.R
import com.example.mykotlin.data.model.Contact
import kotlinx.android.synthetic.main.activity_add.*
import java.nio.file.Files.find

class AddActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//뒤로가기 버튼추가


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

    override fun onOptionsItemSelected(item: MenuItem):Boolean{

        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return false
    }

}