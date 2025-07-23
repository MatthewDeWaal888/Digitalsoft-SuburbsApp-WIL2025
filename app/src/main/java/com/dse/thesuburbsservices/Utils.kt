package com.dse.thesuburbsservices

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import androidx.core.view.isVisible

fun showLoadingScreen(context: Context): Dialog
{
    val dlgLoadingScreen = Dialog(context)
    dlgLoadingScreen.setContentView(R.layout.layout_loading_phone_light)
    dlgLoadingScreen.show()

    return dlgLoadingScreen
}

fun getPercentage(dialog: Dialog): Int
{
    val tvPercentage = dialog.findViewById<TextView>(R.id.tvPercentage)

    return tvPercentage.text.substring(0, tvPercentage.text.length-1).toInt()
}

fun setPercentage(dialog: Dialog, percentage: Int)
{
    val tvPercentage = dialog.findViewById<TextView>(R.id.tvPercentage)
    tvPercentage.text = "$percentage%"
}

fun showPercentage(dialog: Dialog)
{
    val tvPercentage = dialog.findViewById<TextView>(R.id.tvPercentage)
    tvPercentage.isVisible = true
}

fun hidePercentage(dialog: Dialog)
{
    val tvPercentage = dialog.findViewById<TextView>(R.id.tvPercentage)
    tvPercentage.isVisible = false
}