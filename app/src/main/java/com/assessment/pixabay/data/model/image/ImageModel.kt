package com.assessment.pixabay.data.model.image

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageModel(
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "largeImageURL")
    val largeImageURL : String,
    @ColumnInfo(name = "lastSelected")
    var lastSelected: String?


){
    fun lastSelectedString() : String{
        if (lastSelected != null)
            return "Last Selected: $lastSelected"
        return ""
    }

}

