package com.dse.thesuburbsservices.tools

import com.dse.thesuburbsservices.EMPTY_STRING
import org.jsoup.Jsoup

class ArticleHelper(articleLink: String) {

    private var title: String = EMPTY_STRING
    private var date: String = EMPTY_STRING
    private var heading: String = EMPTY_STRING
    private var content: String = EMPTY_STRING

    init {
        val doc = Jsoup.parse(articleLink)

        title = doc.getElementsByClass("elementor-heading-title elementor-size-default")[0].text()
        date = doc.getElementsByClass("elementor-icon-list-text elementor-post-info__item elementor-post-info__item--type-date")[0].text()
        heading = doc.getElementsByClass("wp-block-heading has-text-align-left")[0].text()

        val elem = doc.getElementsByClass("elementor-widget-container")[0]
        elem.child(0).remove()

        content = elem.text()
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
}