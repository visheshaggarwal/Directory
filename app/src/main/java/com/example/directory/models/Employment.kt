package com.example.directory.models

import com.google.gson.annotations.SerializedName

data class Employment(
    @SerializedName("key_skill") val keySkill: String,
    @SerializedName("title") val title: String
)