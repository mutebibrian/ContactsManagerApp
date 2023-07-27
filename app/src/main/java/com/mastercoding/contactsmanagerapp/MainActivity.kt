package com.mastercoding.contactsmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mastercoding.contactsmanagerapp.myviewModel.UserViewModel
import com.mastercoding.contactsmanagerapp.myviewModel.UserViewModelFactory
import com.mastercoding.contactsmanagerapp.databinding.ActivityMainBinding
import com.mastercoding.contactsmanagerapp.room.User
import com.mastercoding.contactsmanagerapp.room.UserDatabase
import com.mastercoding.contactsmanagerapp.room.UserRepository
import com.mastercoding.contactsmanagerapp.viewUI.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Room database
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)


        userViewModel = ViewModelProvider(this,
                factory).get(UserViewModel::class.java)

        binding.userViewModel = userViewModel


        binding.lifecycleOwner = this

        initRecyclerView()

    }

    private fun initRecyclerView()
    {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        userViewModel.users.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, {selectedItem: User -> listItemClicked(selectedItem)}
            )
        })
    }

    private fun listItemClicked(selectedItem: User) {
        Toast.makeText(this,
            "Selected name is ${selectedItem.name}",
            Toast.LENGTH_LONG).show()

        userViewModel.initUpdateAndDelete(selectedItem)


    }
}