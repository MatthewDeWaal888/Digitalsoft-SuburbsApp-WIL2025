package com.dse.thesuburbsservices.pages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.AppData
import com.dse.thesuburbsservices.net.GET_BYTES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class _40Kids40SmilesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment__40_kids40_smiles_phone_light, container, false)

        // Obtain the views from the root view.
        val tvHeading1 = view.findViewById<TextView>(R.id.tvHeading1)
        val tvContent1 = view.findViewById<TextView>(R.id.tvContent1)
        val tvHeading2 = view.findViewById<TextView>(R.id.tvHeading2)
        val tvContent2 = view.findViewById<TextView>(R.id.tvContent2)
        val tvHeading3 = view.findViewById<TextView>(R.id.tvHeading3)
        val layoutView = view.findViewById<LinearLayout>(R.id.layoutView)

        tvHeading1.text = AppData._40kids40smiles?.heading
        tvContent1.text = AppData._40kids40smiles?.content
        tvHeading2.text = AppData._40kids40smiles?.objectivesHeading
        tvContent2.text = AppData._40kids40smiles?.objectives
        tvHeading3.text = AppData._40kids40smiles?.heading2

        // Iteration
        for(i in 0..AppData._40kids40smiles?.images?.size!!-1) {
            // Asynchronous Main Scope
            CoroutineScope(Dispatchers.Main).launch {
                val index = i

                // Declarations
                val layoutView2 = inflater.inflate(R.layout.layout_40kids_40smiles, null)
                val image = layoutView2.findViewById<ImageView>(R.id.imageView)
                val tvImageTitle = layoutView2.findViewById<TextView>(R.id.tvImageTitle)
                var bitmap: Bitmap? = null

                // Obtain the bytes from the requested url.
                val image_bytes = GET_BYTES(AppData?._40kids40smiles?.images!![i])
                // Create a bitmap from the bytes.
                bitmap = BitmapFactory.decodeStream(image_bytes.inputStream())
                // Assign the bitmap to the image view.
                image.setImageDrawable(bitmap.toDrawable(resources))
                // Set the image title.
                tvImageTitle.text = AppData._40kids40smiles?.imageCaptions!![index]

                layoutView.addView(layoutView2)
            }
        }

        return view
    }
}