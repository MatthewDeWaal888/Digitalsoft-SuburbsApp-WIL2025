package com.dse.thesuburbsservices.pages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.EMPTY_STRING
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.ScreenNavigate
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.data.ListingAddress
import com.dse.thesuburbsservices.data.ListingDirectory
import com.dse.thesuburbsservices.hidePercentage
import com.dse.thesuburbsservices.listing_fragment
import com.dse.thesuburbsservices.net.*
import com.dse.thesuburbsservices.showLoadingScreen
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import org.jsoup.*
import org.jsoup.examples.HtmlToPlainText

// This class represents the Listing-Directory fragment.
class ListingDirectoryFragment : Fragment() {

    // Attributes
    lateinit var layoutDisplay: LinearLayout

    // Occurs when the view is created.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null

        if(appTheme == APP_THEME_LIGHT)
            view = inflater.inflate(R.layout.fragment_listing_directory_phone_light, container, false)
        else
            view = inflater.inflate(R.layout.fragment_listing_directory_phone_dark, container, false)

        // Obtain the layoutDisplay view.
        layoutDisplay = view.findViewById<LinearLayout>(R.id.layoutDisplay)
        loadContent(AppData.listings.toTypedArray().filter { it.imageUrl != EMPTY_STRING}.toTypedArray())

        return view
    }

    // Loads the content onto the layoutDisplay.
    fun loadContent(value: Array<ListingDirectory>?)
    {
        // Check if the size of the array is greater than zero.
        if(value?.size!! > 0) {
            // Iterate through the array.
            for(item in value)
            {
                // Inflate a layoutView object.
                val layoutView = this.layoutInflater.inflate(R.layout.layout_listing_directory_phone_light, null)

                // Obtain the child views from the layoutView.
                val clImage = layoutView.findViewById<ConstraintLayout>(R.id.clImage)
                val tvDisplay = layoutView.findViewById<TextView>(R.id.tvDisplay)
                // Set the text for the tvDisplay view.
                tvDisplay.text = item.text

                tvDisplay.setOnClickListener {
                    listing_onclick(item)
                }

                // Create a Bitmap object.
                var bitmap: Bitmap? = null
                // Convert any asynchronous operation to synchronous, using
                // the runBlocking method.
                CoroutineScope(Dispatchers.Main).launch {
                    // Get the image (asynchronous).
                    val image = GET_BYTES(item.imageUrl)
                    // Create a Bitmap object from the byte array.
                    bitmap = BitmapFactory.decodeStream(image!!.inputStream())
                }.invokeOnCompletion {
                    // Assign the bitmap to the clImage view.
                    clImage.background = bitmap?.toDrawable(this.requireContext().resources)

                    clImage.setOnClickListener {
                        listing_onclick(item)
                    }

                    // Add the layoutView to the layoutDisplay view.
                    this.layoutDisplay.addView(layoutView)
                }
            }
        }
    }

    // Occurs when the user selected a listing to view.
    private fun listing_onclick(listing: ListingDirectory)
    {
        val dlg = showLoadingScreen(this.requireContext())
        hidePercentage(dlg)

        // Run synchronously.
        CoroutineScope(Dispatchers.Main).launch {
            val imageContent = GET_BYTES(listing.imageUrl)
            val bitmap = BitmapFactory.decodeStream(imageContent!!.inputStream())

            AppData.listingImage = bitmap

            val contentHtml = GET(listing.contentUrl)
            val doc = Jsoup.parse(contentHtml)
            val pf_body_html = doc.getElementsByClass("pf-body")[0]
            val converter = HtmlToPlainText()
            val plainText = converter.getPlainText(pf_body_html)

            AppData.listingContent = plainText
        }.invokeOnCompletion {
            dlg.dismiss()
            AppData.listingName = listing.text
            AppData.listingLocation = Json.decodeFromString<ListingAddress>(
                listing.listingAddress.substring(
                    1,
                    listing.listingAddress.length - 1
                )
            )

            // Navigate to the listing fragment.
            ScreenNavigate(listing_fragment)
        }
    }
}