package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.CorporateService
import com.dse.thesuburbsservices.net.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class CorporateServiceHelper {

    private var corporateService = CorporateService()
    var onDataReceived: ValueCallback<CorporateService>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "suburbs-corporate")

        // Asynchronous I/O operation.
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            corporateService.heading = doc.getElementsByClass("elementor-element elementor-element-50eb1f1 elementor-widget__width-initial elementor-widget elementor-widget-heading")[0].child(0).child(0).text()
            corporateService.content = doc.getElementsByClass("elementor-element elementor-element-9267d1b elementor-widget-mobile__width-inherit elementor-widget elementor-widget-text-editor")[0].child(0).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(corporateService)
        }
    }

    fun getData(): CorporateService
    {
        return corporateService
    }
}