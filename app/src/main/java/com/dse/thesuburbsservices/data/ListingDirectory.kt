package com.dse.thesuburbsservices.data

import android.graphics.Bitmap
import com.dse.thesuburbsservices.EMPTY_STRING
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class ListingDirectory(
    var listingId: String = EMPTY_STRING,
    var listingAddress: String = EMPTY_STRING,
    var imageUrl: String = EMPTY_STRING,
    var text: String = EMPTY_STRING,
    var contentUrl: String = EMPTY_STRING
)
