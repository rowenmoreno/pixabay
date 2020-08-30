package com.assessment.pixabay.data.model

import com.assessment.pixabay.data.model.image.ImageModel

data class ImagesResponse(
    val hits : List<ImageModel>

)