package com.dse.thesuburbsservices.data

import android.graphics.Bitmap
import com.dse.thesuburbsservices.EMPTY_STRING

class AppData {
    companion object {
        // Selected listing.
        var listingImage: Bitmap? = null
        var listingName: String = EMPTY_STRING
        var listingContent: String = EMPTY_STRING
        var listingLocation: ListingAddress? = null

        // Selected Article.
        var selectedArticle = ArticleData()

        // 40Kids40Smiles
        var _40kids40smiles: _40Kids40Smiles? = null

        // ZachGivesBack
        var zachGivesBack: ZachGivesBack? = null

        // GarthMyMate
        var garthMyMate: GarthMyMate? = null

        // AboutUs
        var aboutUs: AboutUs? = null

        // SportsService
        var sportsService: SportsService? = null

        // HospitalityService
        var hospitalityService: HospitalityService? = null

        // CorporateService
        var corporateService: CorporateService? = null

        // AdvertiseWithUs
        var advertiseWithUs: AdvertiseWithUs? = null

        // CSR
        var csr: CSR? = null

        // Collections
        var listings = ArrayList<ListingDirectory>()
        var articles = ArrayList<Article>()
    }
}