package com.assessment.pixabay.data.model.image

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ImageModelDao {
    @Query("SELECT * FROM ImageModel")
    fun fetchImages() : LiveData<List<ImageModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveImage(value: List<ImageModel>) : List<Long>

    @Update
    suspend fun update(image: ImageModel)
}