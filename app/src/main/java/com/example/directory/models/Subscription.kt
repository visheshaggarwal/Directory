package com.example.directory.models

import com.google.gson.annotations.SerializedName

data class Subscription(
    @SerializedName("payment_method") val paymentMethod: String,
    @SerializedName("plan") val plan: String,
    @SerializedName("status") val status: String,
    @SerializedName("term") val term: String
)