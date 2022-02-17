package com.example.directory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.directory.databinding.ActivityUserBinding
import com.example.directory.models.User
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Gson().fromJson(intent.getStringExtra("USER"), User::class.java)
        binding.tvEmail.text = user.email
        binding.tvName.text = "${user.firstName} ${user.lastName}"
        binding.tvMobile.text = user.phoneNumber
        Picasso.with(this)
            .load(user.avatar)
            .into(binding.avatar, object : Callback {
                override fun onSuccess() {
                    binding.card.visibility = View.VISIBLE
                }
                override fun onError() {
                    binding.avatar.setImageResource(R.drawable.blank_avatar)
                }
            })
    }
}