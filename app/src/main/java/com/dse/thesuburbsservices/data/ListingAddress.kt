package com.dse.thesuburbsservices.data

import com.dse.thesuburbsservices.EMPTY_STRING
import kotlinx.serialization.Serializable

// This class represents a ListingAddress object.
@Serializable
data class ListingAddress(
    var address: String = EMPTY_STRING,
    var lat: Double = 0.0,
    var lng: Double = 0.0
)
