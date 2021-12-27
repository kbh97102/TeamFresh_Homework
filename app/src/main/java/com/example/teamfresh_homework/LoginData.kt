package com.example.teamfresh_homework

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("userId")
    val userID: String,
    @SerializedName("userPwd")
    val userPW: String,
    @SerializedName("appVersion")
    val appVersion: Int = 35
)
