package com.dse.thesuburbsservices.tools

import android.content.Context
import android.graphics.Bitmap
import android.service.carrier.CarrierMessagingService.ResultCallback
import android.util.AttributeSet
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dse.thesuburbsservices.EMPTY_STRING
import com.dse.thesuburbsservices.data.ListingDirectory
import com.dse.thesuburbsservices.net.GET
import com.dse.thesuburbsservices.net.TSS_ADDRESS
import com.dse.thesuburbsservices.net.combinePath
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.jsoup.*
import kotlin.coroutines.suspendCoroutine


class ListingDirectoryHelper(context: Context) {

    private var webView: WebView? = null
    private var listings: Array<ListingDirectory>? = null
    private val jsonConvert = Json { encodeDefaults = true }

    lateinit var onDataReceived: ValueCallback<Array<ListingDirectory>>

    init {
        webView = WebView(context)
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = false

        webView?.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val script = "function getListings() { let listings = document.getElementsByClassName('col-lg-3 col-md-3 col-sm-6 col-xs-12 grid-item');let result = [];for(let i = 0; i < listings.length; i++){let obj = new Object();obj.listingId = listings[i].childNodes[0].getAttribute('data-id');obj.listingAddress = JSON.parse(listings[i].childNodes[0].getAttribute('data-locations')).address;obj.imageUrl = listings[i].childNodes[0].getAttribute('data-thumbnail');obj.text = listings[i].childNodes[0].getElementsByClassName('case27-primary-text listing-preview-title')[0].innerText;obj.contentUrl = listings[i].childNodes[0].childNodes[0].childNodes[0].getAttribute('href');result[i] = obj;}return JSON.stringify(result); } getListings();"

                view?.evaluateJavascript(script) { result ->
                    val json = result.substring(1, result.length-1).replace("\\\"", "\"")
                    listings = Json.decodeFromString(
                        ListSerializer(ListingDirectory.serializer()),
                        json
                    ).toList().toTypedArray()

                    onDataReceived.onReceiveValue(listings)
                }
            }
        }

        val url = combinePath(TSS_ADDRESS, "listing-directory")
        webView?.loadUrl(url)
    }

    fun getListings(): Array<ListingDirectory>?
    {
        return listings
    }
}