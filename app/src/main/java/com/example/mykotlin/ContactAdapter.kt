package com.example.mykotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlin.data.model.Contact

/**
 * 리사이클러뷰의 어댑터는 기본 BaseAdapter()가 아닌, RecyclerView.Adapter 를 extend 해야 한다.
 * 그런데 이 RecyclerView.Adapter는 ViewHolder라는것이 필요한데, 이것을 inner class형식으로
 * 만들어진다. (아래 구현된 것 참조) (unit리턴값은 아무것도 반환하지 않는다는 의미)
 * **/
class ContactAdapter(val contactItemClick: (Contact) -> Unit, val contactItemLongClick: (Contact) -> Unit)
      : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    private var contacts: List<Contact> = listOf()


    /**
     * 아래 3개메소드는 RecyclerView를 상속하면 오버라이딩해야하는 메소드다.
     * onCreateViewHolder: 화면을 최초로딩하여 만들어진 View가 없을때 xml파일을 inflate하여 ViewHolder를 생성한다.
     * getItemCount: RecyclerView로 만들어지는 item의 총 갯수를 반환한다.
     * onBindViewHolder: 위의 onCreateViewHolder에서 만든 View와 실제 입력되는 각각의 데이터를 연결한다.
     */

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(contacts[position])
    }

    //ViewHolder는 RecyclerView안에 들어갈 Item에 대한 View를 인자로 갖는다.
    //id와 데이터를 매핑하는 역할을 담당한다.
    //데이터를 삽입하는 곳으로, inner class이기때문에, ContactAdapter.ViewHolder로 접근하고,
    //RecyclerView.Adapter<>의 인자로 활용한다.
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val nameTv = itemView.findViewById<TextView>(R.id.item_tv_name)
        private val numberTv = itemView.findViewById<TextView>(R.id.item_tv_number)
        private val initialTv = itemView.findViewById<TextView>(R.id.item_tv_initial)

        //이후 이 Adapter를 오버라이딩하는 곳에서 사용한다.
        fun bind(contact: Contact){
            nameTv.text = contact.name
            numberTv.text = contact.number
            initialTv.text = contact.initial.toString()

            //RecyclerView의 클릭이벤트리스너를 사용할 수 있는것은
            //RecyclerView를 상속받았기 때문이다.
            itemView.setOnClickListener{
                contactItemClick(contact)
            }

            itemView.setOnLongClickListener{
                contactItemLongClick(contact)
                true
            }
        }
    }

    fun setContacts(contacts: List<Contact>){
        this.contacts = contacts
        notifyDataSetChanged()
    }

}