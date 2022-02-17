package com.example.directory.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.directory.models.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDAO(): UserDAO
}