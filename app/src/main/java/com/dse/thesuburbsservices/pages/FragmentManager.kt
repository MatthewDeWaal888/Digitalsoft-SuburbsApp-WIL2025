package com.dse.thesuburbsservices.pages

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

val home_fragment = HomeFragment()
val listingDirectory_fragment = ListingDirectoryFragment()
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