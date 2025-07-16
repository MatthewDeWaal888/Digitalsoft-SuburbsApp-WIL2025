package com.dse.thesuburbsservices.pages

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.ListingDirectory
import com.dse.thesuburbsservices.net.*
import com.dse.thesuburbsservices.tools.ListingDirectoryHelper
import kotlinx.coroutines.*
import org.jsoup.*
import org.jsoup.parser.*
import kotlin.io.encoding.Base64

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
        val view = inflater.inflate(R.layout.fragment_listing_directory_phone_light, container, false)

        // Obtain the layoutDisplay view.
        layoutDisplay = view.findViewById<LinearLayout>(R.id.layoutDisplay)

        // Declare and instantiate a TextView object.
        val loadingMessage = TextView(this.requireContext())
        // Assign the text to the TextView.
        loadingMessage.text = "Loading content, please wait..."
        // Set the text color.
        loadingMessage.setTextColor(resources.getColor(R.color.black))
        // Add the textview to the layoutDisplay.
        layoutDisplay.addView(loadingMessage)

        // Declare and instantiate a ListingDirectoryHelper object.
        val helper = ListingDirectoryHelper(this.requireContext())
        // Assign  the ValueCallback listener.
        helper.onDataReceived = object : ValueCallback<Array<ListingDirectory>> {
            override fun onReceiveValue(value: Array<ListingDirectory>?) {
                layoutDisplay.removeView(loadingMessage)
                loadContent(value)
            }
        }

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
                val layoutView = this.layoutInflater.inflate(R.layout.layout_listing_directory_phone, null)

                // Obtain the child views from the layoutView.
                val clImage = layoutView.findViewById<ConstraintLayout>(R.id.clImage)
                val tvDisplay = layoutView.findViewById<TextView>(R.id.tvDisplay)
                // Set the text for the tvDisplay view.
                tvDisplay.text = item.text

                // Create a Bitmap object.
                var bitmap: Bitmap? = null
                // Convert any asynchronous operation to synchronous, using
                // the runBlocking method.
                runBlocking {
                    // Get the image (asynchronous).
                    val image = GET_BASE64(item.imageUrl)
                    // Convert the base64 string to bytes.
                    val bytes = Base64.decode(image)
                    // Create a Bitmap object from the byte array.
                    bitmap = BitmapFactory.decodeStream(bytes.inputStream())
                }
                // Assign the bitmap to the clImage view.
                clImage.background = bitmap?.toDrawable(this.requireContext().resources)
                // Add the layoutView to the layoutDisplay view.
                this.layoutDisplay.addView(layoutView)
            }
        }
    }
}