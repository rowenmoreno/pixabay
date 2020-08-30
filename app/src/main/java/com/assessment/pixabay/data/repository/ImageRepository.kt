package com.assessment.pixabay.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.assessment.pixabay.data.model.image.ImageModel
import com.assessment.pixabay.data.model.image.ImageModelDao

class ImageRepository(private val imageDao: ImageModelDao) {

    val allImage: LiveData<List<ImageModel>> = imageDao.fetchImages()

    suspend fun insert(image: List<ImageModel>) {
        imageDao.saveImage(image)
    }

    suspend fun update(image: ImageModel) {
        imageDao.update(image)
    }

}