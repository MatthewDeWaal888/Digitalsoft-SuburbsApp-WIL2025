package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.data.ZachGivesBack
import com.dse.thesuburbsservices.net.GET
import com.dse.thesuburbsservices.net.TSS_ADDRESS
import com.dse.thesuburbsservices.net.combinePath
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class ZachGivesBackHelper {

    private var obj = ZachGivesBack()
    var onDataReceived: ValueCallback<ZachGivesBack>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "corporate-social-responsibility/zachgivesback")

        // Asynchronous I/O operation
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)

            obj.title = doc.getElementsByClass("elementor-heading-title elementor-size-default")[0].text()
            obj.imageUrl = doc.getElementsByClass("attachment-large size-large wp-image-720")[0].attributes()["src"]
            obj.content = doc.getElementsByClass("elementor-element elementor-element-45eca1b6 elementor-widget elementor-widget-text-editor")[0].child(0).text()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(obj)
        }
    }

    fun getData(): ZachGivesBack
    {
        return obj
    }
}