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
import org.jsoup.nodes.Element
import kotlin.coroutines.suspendCoroutine


class ListingDirectoryHelper(context: Context) {

    private var webView: WebView? = null
    private var listings = ArrayList<ListingDirectory>()
    private val jsonConvert = Json { encodeDefaults = true }

    lateinit var onDataReceived: ValueCallback<Array<ListingDirectory>>

    init {
        var pageNum = 1

        webView = WebView(context)
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = false

        webView?.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                getListingDirectory(view) { result ->
                    val json = Json.decodeFromString<String>(result)
                    listings.addAll(
                        Json.decodeFromString(
                            ListSerializer(ListingDirectory.serializer()),
                            json
                        ).toList().toTypedArray()
                    )

                    getContent(view, 1) {
                        getContent(view, 3) {
                            onDataReceived.onReceiveValue(listings.toTypedArray())
                        }
                    }
                }
            }
        }

        val url = combinePath(TSS_ADDRESS, "listing-directory")
        webView?.loadUrl(url)
    }

    private fun getContent(view: WebView?, pageNum: Int, c: ValueCallback<Int>)
    {
        val script3 = "function performClick() { document.getElementsByClassName('job-manager-pagination')[0].childNodes[0].childNodes[$pageNum].childNodes[0].click(); } performClick();"
        view?.evaluateJavascript(script3) { result1 ->
            Thread.sleep(2500)
            view.evaluateJavascript("document.body.innerHTML.toString();") { result2 ->
                val html = Json.decodeFromString<String>(result2)
                val doc = Jsoup.parse(html)

                val _listings = doc.getElementsByClass("col-lg-3 col-md-3 col-sm-6 col-xs-12 grid-item")

                for(elem in _listings)
                {
                    val listingDirectory = ListingDirectory()
                    listingDirectory.listingId = elem.child(0).attr("data-id")
                    listingDirectory.listingAddress = elem.child(0).attr("data-locations")
                    val imagePath = elem.child(0).child(0).child(0).children()[1].attributes()["style"]
                    listingDirectory.imageUrl = imagePath.substring(23, imagePath.length-3)
                    listingDirectory.text = elem.child(0).getElementsByClass("case27-primary-text listing-preview-title")[0].text()
                    listingDirectory.contentUrl = elem.child(0).child(0).child(0).attr("href")
                    listings.add(listingDirectory)
                }

                c.onReceiveValue(0)
            }
        }
    }

    private fun getListingDirectory(view: WebView?, callback: ValueCallback<String>)
    {
        val script = "function getListings() { let listings = document.getElementsByClassName('col-lg-3 col-md-3 col-sm-6 col-xs-12 grid-item');let result = [];for(let i = 0; i < listings.length; i++){let obj = new Object();obj.listingId = listings[i].childNodes[0].getAttribute('data-id');obj.listingAddress = JSON.parse(listings[i].childNodes[0].getAttribute('data-locations')).address;let imagePath = listings[i].childNodes[0].childNodes[0].childNodes[0].children[1].getAttribute('data-bg-image');obj.imageUrl = imagePath.substring(5, imagePath.length-2);obj.text = listings[i].childNodes[0].getElementsByClassName('case27-primary-text listing-preview-title')[0].innerText;obj.contentUrl = listings[i].childNodes[0].childNodes[0].childNodes[0].getAttribute('href');result[i] = obj;}return JSON.stringify(result); } getListings();"

        view?.evaluateJavascript(script, callback)
    }

    private fun getPageCount(): Int
    {
        var pageCount = 0

        runBlocking {
            val url = combinePath(TSS_ADDRESS, "listing-directory")
            val html = GET(url)
            val doc = Jsoup.parse(html)

            val element = doc.getElementsByClass("job-manager-pagination")[0]
            pageCount = element.child(0).children().count() - 1
        }

        return pageCount
    }

    fun getListings(): Array<ListingDirectory>
    {
        return listings.toTypedArray()
    }
}