package com.dse.thesuburbsservices.pages

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dse.thesuburbsservices.R
import com.dse.thesuburbsservices.data.AppData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds


// This class represents a ListingFragment to view a listing.
class ListingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listing_phone_light, container, false)

        val listingImage = view.findViewById<ImageView>(R.id.listingImage)
        val tvListingName = view.findViewById<TextView>(R.id.tvListingName)
        val tvListingContent = view.findViewById<TextView>(R.id.tvListingContent)
        val mapView = view.findViewById<MapView>(R.id.mapView)

        listingImage.setImageBitmap(AppData.listingImage)
        tvListingName.text = AppData.listingName
        tvListingContent.text = AppData.listingContent

        MapsInitializer.initialize(this.requireContext())
        mapView.getMapAsync { map ->
            map.moveCamera(CameraUpdateFactory.newLatLng(LatLng(AppData.listingLocation!!.lat, AppData.listingLocation!!.lng)))
        }

        return view
    }

}