package com.dse.thesuburbsservices.pages

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.AppData
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.*


// This class represents a ListingFragment to view a listing.
class ListingFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listing_phone_light, container, false)

        val listingImage = view.findViewById<ImageView>(R.id.listingImage)
        val tvListingName = view.findViewById<TextView>(R.id.tvListingName)
        val tvListingContent = view.findViewById<TextView>(R.id.tvListingContent)
        val tvLocation = view.findViewById<TextView>(R.id.tvLocation)
        val mapView = view.findViewById<MapView>(R.id.mapView)
        val btnBack = view.findViewById<AppCompatButton>(R.id.btnBack)

        listingImage.setImageBitmap(AppData.listingImage)
        tvListingName.text = AppData.listingName
        tvListingContent.text = AppData.listingContent

        val listingAddress = AppData.listingLocation
        tvLocation.text = listingAddress!!.address

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.controller.setZoom(20.0)
        mapView.controller.setCenter(GeoPoint(listingAddress.lat, listingAddress.lng))

        btnBack.setOnClickListener {
            ScreenNavigate(listingDirectory_fragment)
        }

        return view
    }

}