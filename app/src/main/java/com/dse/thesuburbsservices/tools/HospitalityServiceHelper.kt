package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.HospitalityService
import com.dse.thesuburbsservices.net.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class HospitalityServiceHelper {

    private var hospitalityService = HospitalityService()
    var onDataReceived: ValueCallback<HospitalityService>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "suburbs-hospitality")
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            hospitalityService.heading =
                doc.getElementsByClass("elementor-element elementor-element-496cbd9 elementor-widget__width-initial elementor-widget elementor-widget-heading")[0].child(
                    0
                ).child(0).text()
            hospitalityService.content =
                doc.getElementsByClass("elementor-element elementor-element-e24dcd9 elementor-widget-mobile__width-inherit elementor-widget elementor-widget-text-editor")[0].child(
                    0
                ).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(hospitalityService)
        }
    }

    fun getData(): HospitalityService
    {
        return hospitalityService
    }
}