package com.dse.thesuburbsservices.data

import android.graphics.Bitmap
import com.dse.thesuburbsservices.EMPTY_STRING
import kotlinx.serialization.Serializable

// ArticleData
data class ArticleData(
    var title: String = EMPTY_STRING,
    var date: String = EMPTY_STRING,
    var heading: String = EMPTY_STRING,
    var content: String = EMPTY_STRING,
    var image: Bitmap? = null
)
