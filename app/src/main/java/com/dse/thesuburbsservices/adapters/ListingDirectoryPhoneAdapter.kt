package com.dse.thesuburbsservices.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.ListingDirectory

class ListingDirectoryPhoneAdapter(context: Context, data: Array<ListingDirectory>) : ArrayAdapter<ListingDirectory>(context, R.layout.layout_listing_directory_phone, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = if(convertView == null) (this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.layout_listing_directory_phone, parent , false) else convertView

        val clImage = view.findViewById<ConstraintLayout>(R.id.clImage)
        val tvDisplay = view.findViewById<TextView>(R.id.tvDisplay)
        val item = getItem(position)

        clImage.background = item?.image?.toDrawable(this.context.resources)
        tvDisplay.text = item?.text

        return view
    }
}