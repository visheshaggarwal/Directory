package com.example.directory.services

import com.example.directory.api.UserAPI
import com.example.directory.dao.UserDatabase
import com.example.directory.models.User
import io.reactivex.Observable

class UserService(private val userAPI: UserAPI, private val database: UserDatabase) {

    fun getNewUsers(numOfUsers: Int): Observable<ArrayList<User>> {
        return userAPI.getNewUsers(numOfUsers)
    }

    fun getUsers() = database.userDAO().getUsers()

    fun insertUser(user: User) {
        database.userDAO().insertUser(user)
    }

    fun deleteAllUsers() {
        database.userDAO().deleteAllUsers()
    }

}