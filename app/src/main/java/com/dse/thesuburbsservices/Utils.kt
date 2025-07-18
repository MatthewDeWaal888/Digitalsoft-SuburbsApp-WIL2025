package com.dse.thesuburbsservices

import android.app.Dialog
import android.content.Context

fun showLoadingScreen(context: Context): Dialog
{
    val dlgLoadingScreen = Dialog(context)
    dlgLoadingScreen.setContentView(R.layout.layout_loading_phone_light)
    dlgLoadingScreen.show()

    return dlgLoadingScreen
}