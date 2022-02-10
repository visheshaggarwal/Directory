package com.example.directory.api

import com.example.directory.models.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UserService {

    @GET("/api/users/random_user")
    fun getUser(): Observable<User>

}