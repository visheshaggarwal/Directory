package com.example.directory.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R
import com.example.directory.models.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var usersList: ArrayList<User> = arrayListOf()
    lateinit var onClickListener: OnClickListener

    fun setListener(listener: OnClickListener) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false),
            onClickListener
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun submitList(updatedUsersList: ArrayList<User>) {
        val userDiffUtils = UserDiffUtils(usersList, updatedUsersList)
        val results = DiffUtil.calculateDiff(userDiffUtils)
        usersList = ArrayList(updatedUsersList)
        results.dispatchUpdatesTo(this)
    }

    class UserViewHolder constructor(itemView: View, onClickListener: OnClickListener) : RecyclerView.ViewHolder(itemView){
        private val avatar = itemView.findViewById<ImageView>(R.id.list_item_avatar)
        private val name = itemView.findViewById<TextView>(R.id.list_item_name)
        private val email = itemView.findViewById<TextView>(R.id.list_item_email)

        init {
            itemView.setOnClickListener {
                onClickListener.onClick(adapterPosition)
            }
        }


        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            name.setText("${user.firstName} ${user.lastName}")
            email.setText(user.email)
            Picasso.with(itemView.context)
                .load(user.avatar)
                .into(avatar, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError() {
                        avatar.setImageResource(R.drawable.blank_avatar)
                    }
                })

        }

    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

}