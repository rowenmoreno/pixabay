package com.assessment.pixabay.data.requests

import com.assessment.pixabay.data.model.ImagesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("api/?key=18087710-c6e0a09c4b9f7e6dcd90211e9&image_type=photo&pretty=true")
    fun getImages(): Call<ImagesResponse>

    companion object{
        operator fun invoke() : ApiService{
            return Retrofit.Builder()
                    .baseUrl("https://pixabay.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        }
    }
}