package com.example.teamfresh_homework

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.teamfresh_homework.databinding.ActivityMainBinding
import com.example.teamfresh_homework.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val service = RetrofitService()
        val resultLiveData = MutableLiveData<String>()

        resultLiveData.observe(this, {
            binding.resultMessage.text = it
        })

        binding.apply {
            loginButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    service.requestLogin(
                        loginIdInput.text.toString(),
                        loginPwInput.text.toString(),
                        resultLiveData
                    )
                }
                val inputManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }
}