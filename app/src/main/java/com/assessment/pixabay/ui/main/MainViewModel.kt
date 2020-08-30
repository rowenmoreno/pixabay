package com.assessment.pixabay.ui.main

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assessment.pixabay.data.db.AppDatabase
import com.assessment.pixabay.data.model.ImagesResponse
import com.assessment.pixabay.data.model.image.ImageModel
import com.assessment.pixabay.data.repository.ImageRepository
import com.assessment.pixabay.data.requests.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainViewModel(application: Application): AndroidViewModel(application) {

    private val imagesResponse: MutableLiveData<ImagesResponse> = MutableLiveData()
    val images: LiveData<List<ImageModel>>
    private val repository: ImageRepository


    init {
        val imagesDao = AppDatabase.getDatabase(application).imageDao()
        repository = ImageRepository(imagesDao)
        images = repository.allImage
    }

    fun getImagesResponse() : LiveData<ImagesResponse>{
        return imagesResponse
    }


    fun fetchImages(){
        ApiService().getImages().enqueue(object : Callback<ImagesResponse>{
            override fun onFailure(call: Call<ImagesResponse>, t: Throwable?) {
                imagesResponse.value = null
            }

            override fun onResponse(call: Call<ImagesResponse>?, response: Response<ImagesResponse>) {
                if (response.isSuccessful){
                    insert(response.body().hits)
                }else{

                }
            }
        })
    }

    fun insert(images: List<ImageModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(images)
    }

    fun update(image: ImageModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(image)
    }



}