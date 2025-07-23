package com.dse.thesuburbsservices.data

import com.dse.thesuburbsservices.EMPTY_STRING

// AboutUs
data class AboutUs(
    var ourStoryHeading: String = EMPTY_STRING,
    var ourStory: String = EMPTY_STRING,
    var ourVisionHeading: String = EMPTY_STRING,
    var ourVision: String = EMPTY_STRING
)
