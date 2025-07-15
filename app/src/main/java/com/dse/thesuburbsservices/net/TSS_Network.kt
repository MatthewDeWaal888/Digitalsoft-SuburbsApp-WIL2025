package com.dse.thesuburbsservices.net

import com.dse.thesuburbsservices.EMPTY_STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okio.IOException
import java.util.Dictionary
import kotlin.io.encoding.Base64

val TSS_ADDRESS = "https://www.thesuburbsservices.co.za"

// Sends a GET operation TO A URL/
suspend fun GET(request_url: String): String
{
    // Holds the result (html).
    var html_response = ""

    // Asynchronous operation.
    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(request_url).build()

        val response = client.newCall(request).execute()
        html_response = response.body.string()
    }.join()

    return html_response
}

// Sends A GET operation to the URL, and returns a Base64 string of the content.
suspend fun GET_BASE64(request_url: String): String
{
    var base64String = EMPTY_STRING

    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(request_url).build()

        val response = client.newCall(request).execute()
        val bytes = response.body.bytes()
        base64String = Base64.encode(bytes)
    }.join()

    return base64String
}

// Sends a POST operation to the URL.
suspend fun POST(request_url: String, headers: Dictionary<String, String>, body: String)
{
    CoroutineScope(Dispatchers.IO).launch {
        val request = Request.Builder().url(request_url)

        for (i in 0..headers.size() - 1) {
            request.addHeader(headers.keys().toList()[i], headers.get(headers.keys().toList()[i]))
        }

        val requestBody = RequestBody.create("text/html".toMediaType(), body)
        request.post(requestBody)
    }.join()
}

fun combinePath(rootPath: String, relativePath: String): String
{
    return "$rootPath/$relativePath"
}