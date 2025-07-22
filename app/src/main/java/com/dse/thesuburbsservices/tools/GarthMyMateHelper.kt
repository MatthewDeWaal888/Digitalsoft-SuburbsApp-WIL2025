package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.GarthMyMate
import com.dse.thesuburbsservices.net.GET
import com.dse.thesuburbsservices.net.TSS_ADDRESS
import com.dse.thesuburbsservices.net.combinePath
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class GarthMyMateHelper {

    private var obj = GarthMyMate()
    var onDataReceived: ValueCallback<GarthMyMate>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "corporate-social-responsibility/garth-my-mate")

        // Asynchronous I/O operation
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            obj.title = doc.getElementsByClass("elementor-heading-title elementor-size-default")[0].text()
            obj.heading1 = doc.getElementsByClass("elementor-element elementor-element-28bc82c elementor-widget elementor-widget-heading")[0].child(0).child(0).text()
            obj.content1 = doc.getElementsByClass("elementor-element elementor-element-659df6b elementor-widget elementor-widget-text-editor")[0].child(0).text()
            obj.heading2 = doc.getElementsByClass("elementor-element elementor-element-90272c7 elementor-widget elementor-widget-heading")[0].child(0).child(0).text()
            obj.content2 = doc.getElementsByClass("elementor-element elementor-element-c6a7264 elementor-icon-list--layout-traditional elementor-list-item-link-full_width elementor-widget elementor-widget-icon-list")[0].child(0).child(0).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(obj)
        }
    }

    fun getData(): GarthMyMate
    {
        return obj
    }
}