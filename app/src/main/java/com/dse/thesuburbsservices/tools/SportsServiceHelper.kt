package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.SportsService
import com.dse.thesuburbsservices.net.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class SportsServiceHelper {

    private var sportsService = SportsService()
    var onDataReceived: ValueCallback<SportsService>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "suburbs-sports")
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            sportsService.heading = doc.getElementsByClass("elementor-element elementor-element-adf67c6 elementor-widget elementor-widget-heading")[0].child(0).child(0).text()
            sportsService.content = doc.getElementsByClass("elementor-element elementor-element-5ea531f elementor-widget elementor-widget-text-editor")[0].child(0).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(sportsService)
        }
    }

    fun getData(): SportsService
    {
        return sportsService
    }
}