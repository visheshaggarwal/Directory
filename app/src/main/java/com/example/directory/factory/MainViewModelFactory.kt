package com.example.directory.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.directory.viewmodels.MainViewModel

class MainViewModelFactory(private val applicationContext: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(applicationContext) as T
    }
}