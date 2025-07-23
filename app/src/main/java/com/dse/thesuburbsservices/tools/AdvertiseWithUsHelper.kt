package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.AdvertiseWithUs
import com.dse.thesuburbsservices.data.HospitalityService
import com.dse.thesuburbsservices.net.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class AdvertiseWithUsHelper {

    private var advertiseWithUs = AdvertiseWithUs()
    var onDataReceived: ValueCallback<AdvertiseWithUs>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "advertise-with-us")

        // Asynchronous I/O operation.
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            advertiseWithUs.heading = doc.getElementsByClass("elementor-element elementor-element-a26c558 elementor-widget__width-initial elementor-widget elementor-widget-heading")[0].child(0).child(0).text()
            advertiseWithUs.content = doc.getElementsByClass("elementor-element elementor-element-7fc83c0 elementor-widget-mobile__width-inherit elementor-widget elementor-widget-text-editor")[0].child(0).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(advertiseWithUs)
        }
    }

    fun getData(): AdvertiseWithUs
    {
        return advertiseWithUs
    }
}