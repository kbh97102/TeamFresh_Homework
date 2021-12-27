package com.example.teamfresh_homework.retrofit


import com.example.teamfresh_homework.LoginData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAPI {

    @POST("api/adminmAPI/login/adminAppLogin")
    fun requestLogin(
        @Body loginData : LoginData
    ): Call<ResponseBody>

}