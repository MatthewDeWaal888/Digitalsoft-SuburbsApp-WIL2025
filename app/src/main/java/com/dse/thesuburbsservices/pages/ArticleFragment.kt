package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isVisible
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.showLoadingScreen
import org.jsoup.examples.HtmlToPlainText

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null

        if(appTheme == APP_THEME_LIGHT)
            view = inflater.inflate(R.layout.fragment_article_phone_light, container, false)
        else
            view = inflater.inflate(R.layout.fragment_article_phone_dark, container, false)

        // Get the views from the container.
        val tvArticleTitle = view.findViewById<TextView>(R.id.tvArticleTitle)
        val tvArticleDate = view.findViewById<TextView>(R.id.tvArticleDate)
        val tvArticleHeading = view.findViewById<TextView>(R.id.tvHeading)
        val tvArticleContent = view.findViewById<TextView>(R.id.tvArticleContent)
        val imgArticle = view.findViewById<ImageView>(R.id.imgArticle)

        tvArticleTitle.text = AppData.selectedArticle?.title
        tvArticleDate.text = AppData.selectedArticle?.date
        tvArticleHeading.text = AppData.selectedArticle?.heading
        tvArticleContent.text = AppData.selectedArticle?.content

        if(AppData.selectedArticle?.image != null)
            imgArticle.setImageDrawable(AppData.selectedArticle.image?.toDrawable(this.resources))
        else
        {
            imgArticle.isVisible = false
        }

        return view
    }
}