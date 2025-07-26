package com.dse.thesuburbsservices.pages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.toDrawable
import com.dse.thesuburbsservices.APP_THEME_LIGHT
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.ScreenNavigate
import com.dse.thesuburbsservices.appTheme
import com.dse.thesuburbsservices.csr_fragment
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.net.GET_BYTES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ZachGivesBackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = null

        if(appTheme == APP_THEME_LIGHT)
            view = inflater.inflate(R.layout.fragment_zach_gives_back_phone_light, container, false)
        else
            view = inflater.inflate(R.layout.fragment_zach_gives_back_phone_dark, container, false)

        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        tvContent.text = AppData.zachGivesBack?.content

        val imageView = view.findViewById<ImageView>(R.id.imageView)

        var bitmap: Bitmap? = null
        CoroutineScope(Dispatchers.Main).launch {
            val image_bytes = GET_BYTES(AppData.zachGivesBack?.imageUrl!!)
            bitmap = BitmapFactory.decodeStream(image_bytes.inputStream())
        }.invokeOnCompletion {
            imageView.setImageDrawable(bitmap?.toDrawable(resources))
        }


        val btnBack = view.findViewById<AppCompatButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            ScreenNavigate(csr_fragment)
        }
        
        return view
    }
}