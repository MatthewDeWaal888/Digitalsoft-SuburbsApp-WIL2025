package com.dse.thesuburbsservices.tools

import android.webkit.ValueCallback
import com.dse.thesuburbsservices.EMPTY_STRING
import com.dse.thesuburbsservices.net.GET
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class ArticleHelper(articleLink: String) {

    private var title: String = EMPTY_STRING
    private var date: String = EMPTY_STRING
    private var heading: String = EMPTY_STRING
    private var content: String = EMPTY_STRING
    private var imageUrl: String = EMPTY_STRING

    var onDataReceived: ValueCallback<ArticleHelper>? = null

    init {
        // Asynchronous I/O operation
        CoroutineScope(Dispatchers.IO).launch {
            val html = GET(articleLink)
            val doc = Jsoup.parse(html)

            title = doc.getElementsByClass("elementor-heading-title elementor-size-default")[0].text()
            date = doc.getElementsByClass("elementor-icon-list-text elementor-post-info__item elementor-post-info__item--type-date")[0].child(0).text()
            val _heading = doc.getElementsByClass("wp-block-heading has-text-align-left")

            if(_heading.size>0)
                heading = _heading[0].text()

            val elem = doc.getElementsByClass("elementor-widget-container")[8]
            elem.children()[0].remove()

            content = elem.text()
            val _imageUrl = doc.getElementsByClass("image-wrapper")

            if(_imageUrl.size>0)
                imageUrl = _imageUrl[0].child(0).attributes()["data-src"]
        }.invokeOnCompletion {
            onDataReceived?.onReceiveValue(this)
        }
    }

    fun getArticleTitle(): String
    {
        return title
    }

    fun getArticleDate(): String
    {
        return date
    }

    fun getArticleHeading(): String
    {
        return heading
    }

    fun getArticleContent(): String
    {
        return content
    }

    fun getArticleImageUrl(): String
    {
        return imageUrl
    }
}