package com.dse.thesuburbsservices.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dse.thesuburbsservices.R

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

        

        return view
    }
}