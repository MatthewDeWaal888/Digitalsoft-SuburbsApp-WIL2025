package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.CSR
import com.dse.thesuburbsservices.net.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class CSRHelper {

    private var csr = CSR()
    var onDataReceived: ValueCallback<CSR>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "corporate-social-responsibility")
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            csr.heading = doc.getElementsByClass("elementor-element elementor-element-9e8b6eb elementor-widget__width-initial elementor-widget elementor-widget-heading")[0].child(0).child(0).text()
            csr.content = doc.getElementsByClass("elementor-element elementor-element-df4f85d elementor-widget-mobile__width-inherit elementor-widget elementor-widget-text-editor")[0].child(0).child(0).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(csr)
        }
    }

    fun getData(): CSR
    {
        return csr
    }
}