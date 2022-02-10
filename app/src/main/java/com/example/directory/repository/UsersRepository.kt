package com.example.directory.repository

import com.example.directory.api.UserService
import com.example.directory.models.User
import io.reactivex.Observable

class UsersRepository(private val userService: UserService) {

    fun getUser(): Observable<User> {
        return userService.getUser()
    }

}