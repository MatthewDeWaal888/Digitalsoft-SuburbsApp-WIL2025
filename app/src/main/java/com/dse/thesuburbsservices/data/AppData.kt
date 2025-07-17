package com.dse.thesuburbsservices.data

import android.graphics.Bitmap
import com.dse.thesuburbsservices.EMPTY_STRING

class AppData {
    companion object {
        // Selected listing.
        var listingImage: Bitmap? = null
        var listingName: String = EMPTY_STRING
        var listingContent: String = EMPTY_STRING
        var listingLocation: ListingAddress? = null

        // All listings
        var listings = ArrayList<ListingDirectory>()
    }
}