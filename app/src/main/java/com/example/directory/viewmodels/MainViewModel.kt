package com.example.directory.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.directory.api.RetrofitHelper
import com.example.directory.api.UserAPI
import com.example.directory.dao.UserDatabase
import com.example.directory.models.User
import com.example.directory.services.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(applicationContext: Context): ViewModel() {

    private val userAPI: UserAPI = RetrofitHelper.getInstance().create(UserAPI::class.java)

    // Executing on main thread right now
    private val database = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "userDB").allowMainThreadQueries().build()
    private val userService: UserService = UserService(userAPI, database)
    private val compositeDisposable = CompositeDisposable()
    var usersList: ArrayList<User> = arrayListOf()
    var usersLoaded: LiveData<Boolean> = MutableLiveData()

    init {
        getUsersFromDB()
    }

    @SuppressLint("CheckResult")
    private fun getUsersFromDB() {
        userService.getUsers()
            .subscribe(
                { userList ->
                    userList.forEach { user ->
                        usersList.add(user)
                    }
                    (usersLoaded as MutableLiveData).postValue(true)
                },
                {
                        throwable -> Log.e("Error", throwable.message ?: "onError")
                }
            )
    }

    fun loadNUsers(numOfUsers: Int) {
        compositeDisposable.add(
            userService.getNewUsers(numOfUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { userList ->
                        userList.forEach { user ->
                            userService.insertUser(user)
                            usersList.add(user)
                        }
                        (usersLoaded as MutableLiveData).postValue(true)
                    },
                    {
                            throwable -> Log.e("Error", throwable.message ?: "onError")
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}