package com.dse.thesuburbsservices.pages

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebView
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.ListingDirectory
import com.dse.thesuburbsservices.net.*
import com.dse.thesuburbsservices.tools.ListingDirectoryHelper
import kotlinx.coroutines.*
import org.jsoup.*
import org.jsoup.parser.*

class ListingDirectoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listing_directory_phone_light, container, false)

        val helper = ListingDirectoryHelper(this.requireContext())
        helper.onDataReceived = object : ValueCallback<Array<ListingDirectory>> {
            override fun onReceiveValue(value: Array<ListingDirectory>?) {
                val x = 0
            }
        }

        return view
    }
}