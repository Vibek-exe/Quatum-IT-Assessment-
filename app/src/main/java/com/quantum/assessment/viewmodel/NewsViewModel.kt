package com.quantum.assessment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quantum.assessment.model.Articles
import com.quantum.assessment.model.Data
import com.quantum.assessment.repository.PostRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(private val repository: PostRepository): ViewModel() {

    val postList = MutableLiveData<Data>()

    fun getAllData(){
        val response = repository.getPost()
        response.enqueue(object : Callback<Data> {
            override fun onResponse(
                call: Call<Data>,
                response: Response<Data>
            ) {
                postList.postValue(response.body())
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.e("Fail", "${t.message}")
            }
        })
    }

}