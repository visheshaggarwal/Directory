package com.example.directory.api

import com.example.directory.models.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {

    @GET("/api/users/random_user")
    fun getNewUsers(@Query("size") size: Int = 1): Observable<ArrayList<User>>

}