package com.example.directory.models

import com.google.gson.annotations.SerializedName

data class CreditCard(
    @SerializedName("cc_number") val ccNumber: String
)