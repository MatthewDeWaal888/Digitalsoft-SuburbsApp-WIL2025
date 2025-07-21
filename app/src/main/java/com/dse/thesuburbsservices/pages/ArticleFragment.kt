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
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.showLoadingScreen
import org.jsoup.examples.HtmlToPlainText

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_article_phone_light, container, false)

        val tvArticleTitle = view.findViewById<TextView>(R.id.tvArticleTitle)
        val tvArticleDate = view.findViewById<TextView>(R.id.tvArticleDate)
        val tvArticleHeading = view.findViewById<TextView>(R.id.tvHeading)
        val tvArticleContent = view.findViewById<TextView>(R.id.tvArticleContent)
        val imgArticle = view.findViewById<ImageView>(R.id.imgArticle)

        tvArticleTitle.text = AppData.selectedArticle?.title
        tvArticleDate.text = AppData.selectedArticle?.date
        tvArticleHeading.text = AppData.selectedArticle?.heading
        tvArticleContent.text = AppData.selectedArticle?.content

        if(AppData?.selectedArticle?.image != null)
            imgArticle.setImageDrawable(AppData.selectedArticle?.image?.toDrawable(this.resources))
        else
        {
            imgArticle.isVisible = false
        }

        return view
    }
}