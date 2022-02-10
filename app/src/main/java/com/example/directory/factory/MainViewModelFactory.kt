package com.example.directory.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.directory.databinding.ActivityMainBinding
import com.example.directory.repository.UsersRepository
import com.example.directory.viewmodels.MainViewModel

class MainViewModelFactory(private val usersRepository: UsersRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(usersRepository) as T
    }
}