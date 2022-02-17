package com.example.directory.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.directory.models.User

class UserDiffUtils(private val oldList: ArrayList<User>, private val newList: ArrayList<User>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].uid == newList[newItemPosition].uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].uid != newList[newItemPosition].uid -> {
                false
            }
            oldList[oldItemPosition].avatar != newList[newItemPosition].avatar -> {
                false
            }
            oldList[oldItemPosition].email != newList[newItemPosition].email -> {
                false
            }
            oldList[oldItemPosition].firstName != newList[newItemPosition].firstName -> {
                false
            }
            oldList[oldItemPosition].lastName != newList[newItemPosition].lastName -> {
                false
            }
            oldList[oldItemPosition].phoneNumber != newList[newItemPosition].phoneNumber -> {
                false
            }
            else -> true
        }
    }

}