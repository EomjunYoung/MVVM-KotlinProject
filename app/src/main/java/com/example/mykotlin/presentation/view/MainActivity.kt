package com.example.mykotlin.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykotlin.ContactAdapter
import com.example.mykotlin.presentation.viewModel.ContactViewModel
import com.example.mykotlin.R
import com.example.mykotlin.data.model.Contact
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        // Set contactItemClick & contactItemLongClick lambda
        // 첫번째인자는 한번클릭시 이벤트를 람다식으로, 두번째인자는 길게클릭시 이벤트를
        // 람다식으로 나타낸것이다.
        /** ListView는 setOnClickLister를 통해 각 item별 클릭시 이벤트처리가 가능하지만,
         * RecyclerView는 Adapter에서 별도로 클릭 리스너를 설정해주어야 한다. **/
        val adapter = ContactAdapter({ contact ->
            val intent =
                    Intent(this, AddActivity::class.java)
            intent.putExtra(
                    AddActivity.EXTRA_CONTACT_NAME,
                    contact.name
            )
            intent.putExtra(
                    AddActivity.EXTRA_CONTACT_NUMBER,
                    contact.number
            )
            intent.putExtra(
                    AddActivity.EXTRA_CONTACT_ID,
                    contact.id
            )
            startActivity(intent)
        }, { contact ->
            deleteDialog(contact)
        })

        //ListView Adapter와 다르게 RecyclerView Adapter에서는 LayoutManager를 설정해야한다.
        //이 역할은 view에 각 item을 배치하고, item이 더이상 보이지 않을때 재사용할 것인지 여부를 결정한다.
        //item을 재사용한다면, LayoutManager는 Adapter에게 view 요소를 다른 데이터로 대체할 것인지 묻는다.
        //이를 통해 불필요한 findViewById를 수행하지않고, 앱 성능을 향상시킬 수 있다.
        val lm = LinearLayoutManager(this)
        main_recycleview.adapter = adapter
        main_recycleview.layoutManager = lm
        // 이 설정은 item이 추가되거나 삭제할때 RecyclerView의 크기가 변화되는걸 방지한다.
        // 이렇게하는 이유는 크기변화시 다른 계층의 View 크기에도 영향을 줄수있기때문에 거의 true로 설정해준다.
        main_recycleview.setHasFixedSize(true)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)

        })

        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

//        mypage_button.setOnClickListener{
//            val intent = Intent(this, MypageActivity::class.java)
//            startActivity(intent)
//        }

        mypage_button.setOnClickListener{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
                .setNegativeButton("NO") { _, _ -> }
                .setPositiveButton("YES") { _, _ ->
                    contactViewModel.delete(contact)
                }
        builder.show()
    }


}