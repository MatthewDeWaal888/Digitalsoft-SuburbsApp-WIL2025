package com.dse.thesuburbsservices.data

import com.dse.thesuburbsservices.EMPTY_STRING

data class ContactUs(
    var name: String = EMPTY_STRING,
    var surname: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var phone: String = EMPTY_STRING,
    var message: String = EMPTY_STRING
)
