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


// This class represents a utility function to get all the listing
// directories from The Suburbs Services website.
// This class uses a WebView during the process.
class ListingDirectoryHelper(context: Context) {

    // Attributes
    private var webView: WebView? = null
    private var listings = ArrayList<ListingDirectory>()
    private val jsonConvert = Json { encodeDefaults = true }
    lateinit var onDataReceived: ValueCallback<Array<ListingDirectory>>

    // Initialization scope.
    init {
        // Instantiate a WebView object.
        webView = WebView(context)
        // Assign the settings to the WebView.
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = false

        // Assign the WebViewClient object, to access the listeners.
        webView?.webViewClient = object : WebViewClient() {

            // Occurs when the WebView object is finished loading the webpage.
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                // Gets the listing directories from the request url.
                getListingDirectory(view) { result ->
                    // Obtain the html (in json format) and decode it as plain text.
                    val json = Json.decodeFromString<String>(result)
                    // Add all the objects to the array 'listings'.
                    listings.addAll(
                        Json.decodeFromString(
                            ListSerializer(ListingDirectory.serializer()),
                            json
                        ).toList().toTypedArray()
                    )

                    // Get the content from the second page.
                    getContent(view, 1) {
                        // Get the content from the third page.
                        getContent(view, 3) {
                            // Perform an onDataReceived callback.
                            onDataReceived.onReceiveValue(listings.toTypedArray())
                        }
                    }
                }
            }
        }

        // Combine the base url with the relative path 'listing-directory'.
        val url = combinePath(TSS_ADDRESS, "listing-directory")
        // LOad the url.
        webView?.loadUrl(url)
    }

    // Gets the content, excluding the first page from the Listing Directory page.
    private fun getContent(view: WebView?, pageNum: Int, c: ValueCallback<Int>)
    {
        // script3: JavaScript that will be executed to navigate to the specific page.
        val script3 = "function performClick() { document.getElementsByClassName('job-manager-pagination')[0].childNodes[0].childNodes[$pageNum].childNodes[0].click(); } performClick();"
        // Performs the javascript execution.
        view?.evaluateJavascript(script3) { result1 ->
            // Wait for 2.5 seconds, so that the page can finish loading.
            Thread.sleep(2500)
            // Performs the javascript execution.
            view.evaluateJavascript("document.body.innerHTML.toString();") { result2 ->
                // Ontain the html content from the json string.
                val html = Json.decodeFromString<String>(result2)
                // Create a Document object from the html, by parsing it with the Jsoup framework.
                val doc = Jsoup.parse(html)

                // Obtain all the listings.
                val _listings = doc.getElementsByClass("col-lg-3 col-md-3 col-sm-6 col-xs-12 grid-item")

                // Iterate through the listings.
                for(elem in _listings)
                {
                    // Declare and instantiate a ListingDirectory object.
                    val listingDirectory = ListingDirectory()

                    // Modify the attributes of the object.
                    listingDirectory.listingId = elem.child(0).attr("data-id")
                    listingDirectory.listingAddress = elem.child(0).attr("data-locations")
                    val imagePath = elem.child(0).child(0).child(0).children()[1].attributes()["style"]
                    listingDirectory.imageUrl = imagePath.substring(23, imagePath.length-3)
                    listingDirectory.text = elem.child(0).getElementsByClass("case27-primary-text listing-preview-title")[0].text()
                    listingDirectory.contentUrl = elem.child(0).child(0).child(0).attr("href")

                    // Add the object to the listing array.
                    listings.add(listingDirectory)
                }

                // Perform a callback operation.
                c.onReceiveValue(0)
            }
        }
    }

    // Gets the listing directories from the first page only.
    private fun getListingDirectory(view: WebView?, callback: ValueCallback<String>)
    {
        val script = "function getListings() { let listings = document.getElementsByClassName('col-lg-3 col-md-3 col-sm-6 col-xs-12 grid-item');let result = [];for(let i = 0; i < listings.length; i++){let obj = new Object();obj.listingId = listings[i].childNodes[0].getAttribute('data-id');obj.listingAddress = JSON.parse(listings[i].childNodes[0].getAttribute('data-locations')).address;let imagePath = listings[i].childNodes[0].childNodes[0].childNodes[0].children[1].getAttribute('data-bg-image');obj.imageUrl = imagePath.substring(5, imagePath.length-2);obj.text = listings[i].childNodes[0].getElementsByClassName('case27-primary-text listing-preview-title')[0].innerText;obj.contentUrl = listings[i].childNodes[0].childNodes[0].childNodes[0].getAttribute('href');result[i] = obj;}return JSON.stringify(result); } getListings();"

        view?.evaluateJavascript(script, callback)
    }

    // Gets the number of pages for the listing directories.
    private fun getPageCount(): Int
    {
        var pageCount = 0

        // Perform a synchronous operation (Converts any asynchronous operations to synchronous).
        runBlocking {
            val url = combinePath(TSS_ADDRESS, "listing-directory")
            val html = GET(url)
            val doc = Jsoup.parse(html)

            val element = doc.getElementsByClass("job-manager-pagination")[0]
            pageCount = element.child(0).children().count() - 1
        }

        // Return the page count.
        return pageCount
    }

    // Returns the listings that were collected from the operation.
    fun getListings(): Array<ListingDirectory>
    {
        return listings.toTypedArray()
    }
}