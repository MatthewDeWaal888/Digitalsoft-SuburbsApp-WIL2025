package com.dse.thesuburbsservices.tools

import android.content.Context
import android.webkit.ValueCallback
import com.dse.thesuburbsservices.EMPTY_STRING
import com.dse.thesuburbsservices.data.ListingDirectory
import com.dse.thesuburbsservices.net.GET
import com.dse.thesuburbsservices.net.TSS_ADDRESS
import com.dse.thesuburbsservices.net.combinePath
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.*


// This class represents a utility function to get all the listing
// directories from The Suburbs Services website.
// This class uses a WebView during the process.
class ListingDirectoryHelper() {

    // Attributes
    private var listings = ArrayList<ListingDirectory>()
    private val deferred = CompletableDeferred<Int>()
    var onDataReceived: ValueCallback<Array<ListingDirectory>>? = null

    // Initialization scope.
    init {
        // Combine the base url with the relative path 'listing-directory'.
        val url = combinePath(TSS_ADDRESS, "listing-directory")

        // Get content for first page
        CoroutineScope(Dispatchers.Main).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            // Obtain all the listings.
            val _listings = doc.getElementsByClass("col-lg-3 col-md-3 col-sm-6 col-xs-12 grid-item")

            // Iterate through the listings.
            for (elem in _listings) {
                // Declare and instantiate a ListingDirectory object.
                val listingDirectory = ListingDirectory()

                // Modify the attributes of the object.
                listingDirectory.listingId = elem.child(0).attr("data-id")
                listingDirectory.listingAddress = elem.child(0).attr("data-locations")
                val imagePath = elem.child(0).child(0).child(0).children()[1].attributes()["data-bg-image"]
                listingDirectory.imageUrl = imagePath.substring(5, imagePath.length - 2)
                listingDirectory.text = elem.child(0)
                    .getElementsByClass("case27-primary-text listing-preview-title")[0].text()
                listingDirectory.contentUrl = elem.child(0).child(0).child(0).attr("href")

                if(listingDirectory.imageUrl != EMPTY_STRING) {
                    // Add the object to the listing array.
                    listings.add(listingDirectory)
                }
            }
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(listings.toTypedArray())
            deferred.complete(1)
        }
    }


    // Returns the listings that were collected from the operation.
    fun getListings(): Array<ListingDirectory>
    {
        return listings.toTypedArray()
    }

    fun getTask(): CompletableDeferred<Int>
    {
        return deferred
    }
}