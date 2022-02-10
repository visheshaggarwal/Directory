package com.example.directory.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.directory.models.User
import com.example.directory.repository.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(usersRepository: UsersRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var text: LiveData<User> = MutableLiveData()

    init {
        compositeDisposable.add(
            usersRepository.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { user ->
                        Log.i("Response", user.toString())
                        (text as MutableLiveData).postValue(user)
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