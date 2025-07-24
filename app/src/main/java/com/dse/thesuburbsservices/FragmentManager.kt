package com.dse.thesuburbsservices

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dse.thesuburbsservices.pages.ArticleFragment
import com.dse.thesuburbsservices.pages.CSRFragment
import com.dse.thesuburbsservices.pages.GarthMyMateFragment
import com.dse.thesuburbsservices.pages.HomeFragment
import com.dse.thesuburbsservices.pages.ListingDirectoryFragment
import com.dse.thesuburbsservices.pages.ListingFragment
import com.dse.thesuburbsservices.pages.WhatsHappeningFragment
import com.dse.thesuburbsservices.pages.ZachGivesBackFragment
import com.dse.thesuburbsservices.pages.*

// Fragment pages
val home_fragment = HomeFragment()
val listingDirectory_fragment = ListingDirectoryFragment()
val listing_fragment = ListingFragment()
val whatshappening_fragment = WhatsHappeningFragment()
val article_fragment = ArticleFragment()
val csr_fragment = CSRFragment()
val _40kids40smiles_fragment = _40Kids40SmilesFragment()
val zachGivesBack_fragment = ZachGivesBackFragment()
val garthMyMate_fragment = GarthMyMateFragment()
val contact_fragment = ContactFragment()
val aboutUs_fragment = AboutUsFragment()
val servicesSports_fragment = SportsServiceFragment()
val servicesHospitality_fragment = HospitalityServiceFragment()
val servicesCorporate_fragment = CorporateServiceFragment()
val staffLogin_fragment = StaffLoginFragment()
val advertiseWithUs_fragment = AdvertiseWithUsFragment()


lateinit var tss_fragmentManager: FragmentManager
lateinit var tss_clDisplay: ConstraintLayout


// Navigates to a page (Fragment).
fun ScreenNavigate(page: Fragment)
{
    // Begin a fragment transaction.
    val transaction = tss_fragmentManager.beginTransaction()
    // Assign a page (fragment) to the transaction.
    transaction.replace(tss_clDisplay.id, page)
    // Start the transaction.
    transaction.commit()
}