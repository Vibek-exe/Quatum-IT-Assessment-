package com.quantum.assessment.repository

import com.quantum.assessment.network.ApiInterface

class PostRepository( private val apiInterface: ApiInterface) {
    fun getPost() = apiInterface.getData()
}