package com.dse.thesuburbsservices.tools

import android.content.Context
import android.webkit.ValueCallback
import com.dse.thesuburbsservices.EMPTY_STRING
import com.dse.thesuburbsservices.data.Article
import com.dse.thesuburbsservices.net.GET
import com.dse.thesuburbsservices.net.TSS_ADDRESS
import com.dse.thesuburbsservices.net.combinePath
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class WhatsHappeningHelper() {

    private val articles = ArrayList<Article>()
    var onDataReceived: ValueCallback<Array<Article>>? = null

    init {
        val url = combinePath(TSS_ADDRESS, "whats-happening-in-your-suburb")

        CoroutineScope(Dispatchers.Main).launch {
            val html = GET(url)
            val doc = Jsoup.parse(html)
            val _articles = doc.getElementsByTag("article")
            val result = ArrayList<Article>()

            for(i in _articles)
            {
                val articleImage = if(i.child(0).child(0).child(0).attributes().hasKey("data-src")) i.child(0).child(0).child(0).attributes()["data-src"] else i.child(0).child(0).child(0).attributes()["src"]
                val articleTitle = i.child(1).child(0).child(0).text()
                val articleDate = i.child(1).child(1).child(0).text()
                val articleDescription = i.child(1).child(2).child(0).text()
                val articleReadMoreLink = i.child(1).child(3).attributes()["href"]

                result.add(Article(imageUrl=articleImage, title=articleTitle, date=articleDate, description=articleDescription, readMoreLink=articleReadMoreLink))
            }

            for(i in result)
            {
                if(!containsArticle(articles.toTypedArray(), i.title))
                    articles.add(i)
            }

            onDataReceived?.onReceiveValue(articles.toTypedArray())
        }
    }

    private fun containsArticle(array: Array<Article>, title: String): Boolean
    {
        var result = false

        for(i in array)
        {
            if(i.title == title)
            {
                result = true
            }
        }

        return result
    }

    fun getArticles(): Array<Article>
    {
        return articles.toTypedArray()
    }
}