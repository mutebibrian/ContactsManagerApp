package com.mastercoding.contactsmanagerapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//annotation for dao class.

@Dao
interface UserDAO {
    // below is the insert method for
    // adding a new entry to our database.

    @Insert
    suspend fun insertUser(user: User):Long

    // below is the update method
    // for deleting our details.
    @Update
    suspend fun updateUser(user: User)
    
    
// below is the delete method
    // for deleting our details.
    @Delete
    suspend fun deleteUser(user: User)
    

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    // below is the method to read all contact details
    // from our database we have specified the query for it.
    // order on below line and we are specifying
    // the table name from which
    // we have to get the data from.

    @Query("SELECT * FROM user")
    fun getAllUsersInDB(): LiveData<List<User>>

}
