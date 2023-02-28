package com.quantum.assessment.retrofit


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val BASE_URL: String = "https://newsapi.org/ "
    var retrofit: Retrofit? = null


    fun getApiData(): Retrofit? {
        if (retrofit == null) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
                .addInterceptor(interceptor)


            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}