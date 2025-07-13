package com.dse.thesuburbsservices.data

import android.graphics.Bitmap
import com.dse.thesuburbsservices.EMPTY_STRING

data class ListingDirectory(
    var listingId: String = EMPTY_STRING,
    var listingAddress: String = EMPTY_STRING,
    var image: Bitmap? = null,
    var text: String = EMPTY_STRING,
    var contentUrl: String = EMPTY_STRING
)
