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
import org.jsoup.*
import kotlin.coroutines.suspendCoroutine


class ListingDirectoryHelper(context: Context) {

    var webView: WebView? = null

    init {
        webView = WebView(context)
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = false

        webView?.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val script = "function getListings() { let listings = document.getElementsByClassName('col-lg-3 col-md-3 col-sm-6 col-xs-12 grid-item');let result = [];for(let i = 0; i < listings.length; i++){let obj = new Object();obj.listingId = listings[i].childNodes[0].getAttribute('data-id');obj.listingAddress = listings[i].childNodes[0].getAttribute('data-locations');obj.imageUrl = listings[i].childNodes[0].getAttribute('data-thumbnail');obj.text = listings[i].childNodes[0].getElementsByClassName('case27-primary-text listing-preview-title')[0].innerText;result[i] = obj;}return JSON.stringify(result); } getListings();"

                view?.evaluateJavascript(script) { result ->

                }
            }
        }

        val url = combinePath(TSS_ADDRESS, "listing-directory")
        webView?.loadUrl(url)
    }

}