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
    }.join() // Wait for the operation to complete.

    return html_response
}

// Sends A GET operation to the URL, and returns a Base64 string of the content.
suspend fun GET_BASE64(request_url: String): String
{
    // Holds the Base64 string.
    var base64String = EMPTY_STRING

    // Asynchronous scope.
    CoroutineScope(Dispatchers.IO).launch {
        // Declare and instantiate an OkHttpClient object.
        val client = OkHttpClient.Builder().build()
        // Declare and instantiate a Request object.
        val request = Request.Builder().url(request_url).build()

        // Get the response from the client request.
        val response = client.newCall(request).execute()
        // Obtain the bytes from the response body.
        val bytes = response.body.bytes()
        // Convert the bytes to a Base64 string.
        base64String = Base64.encode(bytes)
    }.join() // Wait for the operation to finish.

    return base64String
}

// Sends A GET operation to the URL, and returns the bytes of the content.
suspend fun GET_BYTES(request_url: String): ByteArray?
{
    var bytes: ByteArray? = null

    // Asynchronous scope.
    CoroutineScope(Dispatchers.IO).launch {
        // Declare and instantiate an OkHttpClient object.
        val client = OkHttpClient.Builder().build()
        // Declare and instantiate a Request object.
        val request = Request.Builder().url(request_url).build()

        // Get the response from the client request.
        val response = client.newCall(request).execute()
        // Obtain the bytes from the response body.
        bytes = response.body.bytes()
        // Convert the bytes to a Base64 string.
    }.join() // Wait for the operation to finish.

    return bytes
}

// Sends a POST operation to the URL.
suspend fun POST(request_url: String, headers: Dictionary<String, String>, body: String)
{
    // Asynchronous scope.
    CoroutineScope(Dispatchers.IO).launch {
        // Create a request from the given url.
        val request = Request.Builder().url(request_url)

        // Iteration
        for (i in 0..headers.size() - 1) {
            // Add a header to the request.
            request.addHeader(headers.keys().toList()[i], headers.get(headers.keys().toList()[i]))
        }

        // Create a request body.
        val requestBody = RequestBody.create("text/html".toMediaType(), body)
        // Post the request.
        request.post(requestBody)
    }.join() // Wait for the operation to finish.
}

// Creates an absolute path from the given root and relative path.
fun combinePath(rootPath: String, relativePath: String): String
{
    return "$rootPath/$relativePath"
}