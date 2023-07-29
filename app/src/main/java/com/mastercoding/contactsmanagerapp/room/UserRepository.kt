package com.mastercoding.contactsmanagerapp.room

class UserRepository(private val dao: UserDAO) {

    val users = dao.getAllUsersInDB()
    // on below line we are creating an insert method
    // for adding the user to our database.

    suspend fun insert(user: User): Long{
        return dao.insertUser(user)
    }
    // on below line we are creating a delete method
    // for deleting our user from database.

    suspend fun delete(user: User) {
        return dao.deleteUser(user)
    }
    // on below line we are creating a update method
    // for deleting our user from database.
    suspend fun update(user: User) {
        return dao.updateUser(user)
    }

    // on below line we are creating a deleteAll method
    // for deleting all our users from database.
    suspend fun deleteAll(){
        return dao.deleteAll()
    }
}


