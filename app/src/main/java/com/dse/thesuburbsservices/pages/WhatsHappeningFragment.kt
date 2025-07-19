package com.dse.thesuburbsservices.pages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.data.Article
import com.dse.thesuburbsservices.net.GET_BYTES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64

class WhatsHappeningFragment : Fragment() {

    private lateinit var layoutArticles: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_whats_happening, container, false)

        layoutArticles = view.findViewById<LinearLayout>(R.id.layoutArticles)
        loadContent(AppData.articles.toTypedArray())

        return view
    }

    private fun loadContent(value: Array<Article>?)
    {
        if(value != null) {
            for (i in value) {
                val layoutArticle = this.layoutInflater.inflate(R.layout.layout_article_phone_light, null)

                val clImage = layoutArticle.findViewById<ConstraintLayout>(R.id.clImage)
                val tvArticleText = layoutArticle.findViewById<TextView>(R.id.tvArticleTitle)
                val tvArticleDate = layoutArticle.findViewById<TextView>(R.id.tvArticleDate)
                val tvArticleDescription = layoutArticle.findViewById<TextView>(R.id.tvArticleDescription)
                val btnReadMore = layoutArticle.findViewById<TextView>(R.id.btnReadMore)


                tvArticleText.text = i.title
                tvArticleDate.text = i.date
                tvArticleDescription.text = i.description

                btnReadMore.setOnClickListener {
                    // Code to read the article
                }

                var bitmap: Bitmap? = null
                CoroutineScope(Dispatchers.Main).launch {
                    if(i.imageUrl.startsWith("data:image"))
                    {
                        val image_base64 = i.imageUrl.substring(i.imageUrl.indexOf(',') + 1)
                        val image_bytes = Base64.decode(image_base64)
                        bitmap = BitmapFactory.decodeStream(image_bytes.inputStream())
                    }
                    else {
                        val image_bytes = GET_BYTES(i.imageUrl)
                        bitmap = BitmapFactory.decodeStream(image_bytes.inputStream())
                    }
                }.invokeOnCompletion {
                    clImage.background = bitmap?.toDrawable(this.resources)

                    this.layoutArticles.addView(layoutArticle)
                }
            }
        }
    }
}