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

class ListingDirectoryFragment : Fragment() {

    lateinit var layoutDisplay: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listing_directory_phone_light, container, false)

        layoutDisplay = view.findViewById<LinearLayout>(R.id.layoutDisplay)

        val helper = ListingDirectoryHelper(this.requireContext())
        helper.onDataReceived = object : ValueCallback<Array<ListingDirectory>> {
            override fun onReceiveValue(value: Array<ListingDirectory>?) {
                loadContent(value)
            }
        }

        return view
    }

    fun loadContent(value: Array<ListingDirectory>?)
    {
        if(value?.size!! > 0) {
            for(item in value)
            {
                val layoutView = this.layoutInflater.inflate(R.layout.layout_listing_directory_phone, null)

                val clImage = layoutView.findViewById<ConstraintLayout>(R.id.clImage)
                val tvDisplay = layoutView.findViewById<TextView>(R.id.tvDisplay)
                tvDisplay.text = item.text

                var bitmap: Bitmap? = null
                runBlocking {
                    val image = GET_BASE64(item.imageUrl)
                    val bytes = Base64.decode(image)
                    bitmap = BitmapFactory.decodeStream(bytes.inputStream())
                }
                clImage.background = bitmap?.toDrawable(this.requireContext().resources)
                this.layoutDisplay.addView(layoutView)
            }
        }
    }
}