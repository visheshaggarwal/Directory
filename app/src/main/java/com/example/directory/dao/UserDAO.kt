package com.example.directory.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.directory.models.User
import io.reactivex.Single

@Dao
interface UserDAO {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getUsers(): Single<List<User>>

    @Query("DELETE FROM user")
    fun deleteAllUsers()
}