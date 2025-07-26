package com.dse.thesuburbsservices.net

import android.content.Context
import android.webkit.ValueCallback
import com.dse.thesuburbsservices.EMPTY_STRING
import com.dse.thesuburbsservices.data.ContactUs
import com.dse.thesuburbsservices.tools.ContactUsHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Dictionary
import kotlin.io.encoding.Base64
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsBytes
import io.ktor.util.StringValuesBuilder

val TSS_ADDRESS = "https://www.thesuburbsservices.co.za"

// Sends a GET operation TO A URL/
suspend fun GET(request_url: String): String
{
    val client = HttpClient(CIO)
    val html_response = client.get(request_url).body<String>()

    return html_response
}

// Sends A GET operation to the URL, and returns a Base64 string of the content.
suspend fun GET_BASE64(request_url: String): String
{
    val client = HttpClient(CIO)
    val bytes = client.get(request_url).bodyAsBytes()
    // Convert the bytes to a Base64 string.
    val base64String = Base64.encode(bytes)

    return base64String
}

// Sends A GET operation to the URL, and returns the bytes of the content.
suspend fun GET_BYTES(request_url: String): ByteArray
{
    val client = HttpClient(CIO)
    val bytes = client.get(request_url).bodyAsBytes()

    return bytes
}

// Creates an absolute path from the given root and relative path.
fun combinePath(rootPath: String, relativePath: String): String
{
    return "$rootPath/$relativePath"
}

// Sends a message to The Suburbs Services organization.
fun TSS_SendMessage(context: Context, contactUs: ContactUs, submissionCompleted: ValueCallback<String>?)
{
    val helper = ContactUsHelper(context)
    helper.onSubmissionCompleted = submissionCompleted
    helper.onReady = object : ValueCallback<Boolean> {
        override fun onReceiveValue(value: Boolean?) {
            helper.setContactUsInfo(contactUs)
            helper.sendMessage()
        }
    }
}