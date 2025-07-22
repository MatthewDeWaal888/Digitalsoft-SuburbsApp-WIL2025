package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.EMPTY_STRING
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
    private var heading: String = EMPTY_STRING
    private var content: String = EMPTY_STRING
    private var objectives: String = EMPTY_STRING
    private var images: Array<String>? = null
    private var imageCaptions: Array<String>? = null

    // Event for data received.
    var onDataReceived: ValueCallback<_40Kids40SmilesHelper>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "corporate-social-responsibility/40-kids-40-smiles")

        // Asynchronous I/O operation.
        CoroutineScope(Dispatchers.IO).launch {
            // Get the html from the response.
            val html = GET(url)
            // Create a Jsoup document from the html code.
            val doc = Jsoup.parse(html)

            // Get the root node.
            val root = doc.getElementsByClass("e-con-inner")[0]

            heading = root.getElementsByClass("elementor-heading-title elementor-size-default")[0].text()
            content = root.getElementsByClass("elementor-widget-container")[14].text()

            val converter = HtmlToPlainText()
            objectives = converter.getPlainText(root.getElementsByClass("elementor-element elementor-element-945677c e-con-full e-flex e-con e-child")[0])

            val _images = root.getElementsByClass("elementor-image-box-img")
            val _imageCaption = root.getElementsByClass("elementor-image-box-title")

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

            images = image_src_list.toTypedArray()
            imageCaptions = image_title_list.toTypedArray()
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(this)
        }
    }

    fun getHeading(): String
    {
        return heading
    }

    fun getContent(): String
    {
        return content
    }

    fun getObjectives(): String
    {
        return objectives
    }

    fun getImages(): Array<String>?
    {
        return images
    }

    fun getCaptions(): Array<String>?
    {
        return imageCaptions
    }
}