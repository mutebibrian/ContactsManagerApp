package com.mastercoding.contactsmanagerapp.myviewModel


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mastercoding.contactsmanagerapp.room.User
import com.mastercoding.contactsmanagerapp.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel(),Observable {




    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete : User

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()


    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }


    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            // Make Update:
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!
            update(userToUpdateOrDelete)

        }else{
            // Insert Functionality
            val name =  inputName.value!!
            val email = inputEmail.value!!

            insert(User(0,name,email))

            inputName.value = null
            inputEmail.value = null
        }



    }

    fun clearAllorDelete(){
        if (isUpdateOrDelete){
            delete(userToUpdateOrDelete)
        }else{
            clearAll()
        }


    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun clearAll()= viewModelScope.launch {
        repository.deleteAll()
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)

        // Resetting the Buttons and Fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value  = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"



    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)

        // Resetting the Buttons and Fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value  = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }


    fun initUpdateAndDelete(user: User){
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        userToUpdateOrDelete = user
        saveOrUpdateButtonText.value  = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }






    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}