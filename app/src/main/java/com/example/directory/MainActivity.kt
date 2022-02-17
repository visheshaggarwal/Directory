package com.example.directory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.directory.adapters.UserListAdapter
import com.example.directory.databinding.ActivityMainBinding
import com.example.directory.factory.MainViewModelFactory
import com.example.directory.viewmodels.MainViewModel
import com.google.gson.Gson
import java.io.Serializable

class MainActivity : AppCompatActivity(){

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)

        binding.directoryList.layoutManager = LinearLayoutManager(this)
        userListAdapter = UserListAdapter()
        binding.directoryList.adapter = userListAdapter
        userListAdapter.setListener(object: UserListAdapter.OnClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(applicationContext, UserActivity::class.java)
                intent.putExtra("USER", Gson().toJson(mainViewModel.usersList[position]))
                startActivity(intent)
            }
        })

        mainViewModel.usersLoaded.observe(this, Observer{
            userListAdapter.submitList(mainViewModel.usersList)
            binding.circularProgress.visibility = View.GONE
            binding.btnLoad.visibility = View.VISIBLE
        })

        binding.btnLoad.setOnClickListener {
            binding.circularProgress.visibility = View.VISIBLE
            binding.btnLoad.visibility = View.GONE
            mainViewModel.loadNUsers(2)
        }

    }



}