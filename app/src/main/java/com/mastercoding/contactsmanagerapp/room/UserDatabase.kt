package com.mastercoding.contactsmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase(){

    abstract val userDAO: UserDAO

    // Singleton Design Pattern
    
    companion object{
        // Singleton prevents multiple
        // instances of database opening at the same time.
        @Volatile
        private var INSTANCE: UserDatabase ?= null
            fun getInstance(context: Context): UserDatabase{
                
                // if the INSTANCE is not null, then return it,
            // if it is, then create the database
                
                synchronized(this){
                    var instance = INSTANCE
                    if (instance == null){
                        // Creating the database object
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java,
                            "users_db"
                        )
                            .build()
                    }
                    INSTANCE = instance
                // return instance

                    return instance
                }
            }
    }

}
