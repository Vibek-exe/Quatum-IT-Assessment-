package com.quantum.assessment.network

import com.quantum.assessment.constant.AppUrls
import com.quantum.assessment.model.Articles
import com.quantum.assessment.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/everything?q=tesla&from=2023-02-27&sortBy=publishedAt&apiKey=dae2d319f96341f9b424d3b419cfd241")
    fun getData(): Call<Data>
}