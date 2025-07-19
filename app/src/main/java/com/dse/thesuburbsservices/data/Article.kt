package com.dse.thesuburbsservices.data

import com.dse.thesuburbsservices.EMPTY_STRING
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    var imageUrl: String = EMPTY_STRING,
    var title: String = EMPTY_STRING,
    var date: String = EMPTY_STRING,
    var description: String = EMPTY_STRING,
    var readMoreLink: String = EMPTY_STRING
)
