package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.AboutUs
import com.dse.thesuburbsservices.net.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class AboutUsHelper {

    private var aboutUs = AboutUs()
    var onDataReceived: ValueCallback<AboutUs>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "about-us")

        // Asynchronous I/O operation
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            aboutUs.ourStory = doc.getElementsByClass("elementor-element elementor-element-6f2880c elementor-widget-mobile__width-inherit elementor-widget elementor-widget-text-editor")[0].child(0).text()
            aboutUs.ourVision = doc.getElementsByClass("elementor-element elementor-element-13a296e elementor-widget-mobile__width-inherit elementor-widget elementor-widget-text-editor")[0].child(0).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(aboutUs)
        }
    }

    fun getData(): AboutUs
    {
        return aboutUs
    }
}