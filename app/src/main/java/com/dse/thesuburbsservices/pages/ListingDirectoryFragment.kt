package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.net.*
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

        val ld_path = combinePath(TSS_ADDRESS, "listing-directory")
        var html = ""

        CoroutineScope(Dispatchers.IO).launch {
            html = GET(ld_path)
        }.invokeOnCompletion {
            val document = Jsoup.parse(html)
        }


        return view
    }
}