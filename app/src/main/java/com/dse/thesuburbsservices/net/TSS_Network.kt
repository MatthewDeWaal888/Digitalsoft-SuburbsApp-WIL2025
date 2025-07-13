package com.dse.thesuburbsservices.net

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okio.IOException
import java.util.Dictionary

val TSS_ADDRESS = "https://www.thesuburbsservices.co.za"

suspend fun GET(request_url: String): String
{
    var html_response = ""

    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(request_url).build()

        val response = client.newCall(request).execute()
        html_response = response.body.string()
    }.join()

    return html_response
}

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