package com.example.directory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.directory.api.RetrofitHelper
import com.example.directory.api.UserService
import com.example.directory.databinding.ActivityMainBinding
import com.example.directory.factory.MainViewModelFactory
import com.example.directory.repository.UsersRepository
import com.example.directory.viewmodels.MainViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)

        val userService = RetrofitHelper.getInstance().create(UserService::class.java)
        val usersRepository = UsersRepository(userService)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(usersRepository)).get(MainViewModel::class.java)
        mainViewModel.text.observe(this, Observer{ user ->
            if(user == null) {
                return@Observer
            }
            Picasso.with(this)
                .load(user.avatar)
                .into(avatar, object: Callback {
                    override fun onSuccess() {
                        //set animations here
                        circular_progress.visibility = View.GONE
                        card.visibility = View.VISIBLE
                        tv_name.text = "${user.first_name} ${user.last_name}"
                        tv_email.text = user.email
                        tv_mobile.text = user.phone_number
                    }

                    override fun onError() {
                        TODO("Not yet implemented")
                    }
                })

        })
    }


}