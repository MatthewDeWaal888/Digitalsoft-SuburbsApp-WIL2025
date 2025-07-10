package com.dse.thesuburbsservices.pages

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


fun ScreenNavigate(fm: FragmentManager, clDisplay: ConstraintLayout, page: Fragment)
{
    val transaction = fm.beginTransaction()
    transaction.replace(clDisplay.id, page)
    transaction.commit()
}