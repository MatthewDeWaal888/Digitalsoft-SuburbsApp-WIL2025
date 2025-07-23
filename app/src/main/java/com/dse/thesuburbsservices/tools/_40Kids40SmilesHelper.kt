package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.EMPTY_STRING
import com.dse.thesuburbsservices.data._40Kids40Smiles
import com.dse.thesuburbsservices.net.GET
import com.dse.thesuburbsservices.net.TSS_ADDRESS
import com.dse.thesuburbsservices.net.combinePath
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.examples.HtmlToPlainText

// _40Kids40SmilesHelper
class _40Kids40SmilesHelper {

    // Attributes/Data fields
    private var obj = _40Kids40Smiles()

    // Event for data received.
    var onDataReceived: ValueCallback<_40Kids40Smiles>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "corporate-social-responsibility/40-kids-40-smiles")

        // Asynchronous I/O operation.
        CoroutineScope(Dispatchers.IO).launch {
            // Get the html from the response.
            val html = GET(url)
            // Create a Jsoup document from the html code.
            val doc = Jsoup.parse(html)

            obj.heading = doc.getElementsByClass("elementor-heading-title elementor-size-default")[0].text()
            obj.content = doc.getElementsByClass("elementor-widget-container")[10].text()

            val converter = HtmlToPlainText()
            obj.objectivesHeading = doc.getElementsByClass("elementor-element elementor-element-867cceb elementor-widget elementor-widget-heading")[0].child(0).child(0).text()
            obj.objectives = doc.getElementsByClass("elementor-widget-container")[12].text()
            obj.heading2 = doc.getElementsByClass("elementor-element elementor-element-67a1aaa elementor-widget elementor-widget-heading")[0].child(0).child(0).text()

            val _images = doc.getElementsByClass("elementor-image-box-wrapper")
            val _imageCaption = doc.getElementsByClass("elementor-image-box-title")

            val image_src_list = ArrayList<String>()
            val image_title_list = ArrayList<String>()

            for(i in _images)
            {
                val data_src = i.child(0).child(0).attributes()["data-src"]
                image_src_list.add(data_src)
            }

            for(i in _imageCaption)
            {
                val title = i.text()
                image_title_list.add(title)
            }

            obj.images = image_src_list.toTypedArray()
            obj.imageCaptions = image_title_list.toTypedArray()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(obj)
        }
    }

    fun getData(): _40Kids40Smiles
    {
        return obj
    }
}