package com.example.teamfresh_homework.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.teamfresh_homework.LoginData
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private var api: RetrofitAPI

    private val retrofit = Retrofit.Builder()
        .baseUrl(" https://tmsapidev.teamfresh.co.kr/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    init {
        api = retrofit.create(RetrofitAPI::class.java)
    }

    fun requestLogin(userID: String, userPW: String, liveData: MutableLiveData<String>) {
        api.requestLogin(LoginData(userID = userID, userPW = userPW))
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val json: JsonObject =
                                JsonParser.parseString(it.string()).asJsonObject
                            liveData.postValue(json.get("resultMsg").asString)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Error", t.message.toString())
                }
            })
    }
}