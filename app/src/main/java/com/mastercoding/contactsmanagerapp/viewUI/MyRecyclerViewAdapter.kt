package com.mastercoding.contactsmanagerapp.viewUI

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mastercoding.contactsmanagerapp.R
import com.mastercoding.contactsmanagerapp.databinding.CardItemBinding
import com.mastercoding.contactsmanagerapp.room.User

class MyRecyclerViewAdapter
// on below line we are creating a
    // variable for our all userslist.
    (private val usersList:List<User>,
        private val clickListener: (User)->Unit
     ) : RecyclerView.Adapter<MyViewHolder>()

{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                // inflating our layout file for each item of recycler view.

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : CardItemBinding = DataBindingUtil.
                inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning our list size.
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
// on below line we are adding click listener
        // to our recycler view item.
        holder.bind(usersList[position],clickListener)
    }


}

        // on below line we are creating a view holder class.

class MyViewHolder(val binding: CardItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(user: User, clickListener: (User) -> Unit){
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email

        binding.listItemLayout.setOnClickListener{
            clickListener(user)
        }

    }


}
