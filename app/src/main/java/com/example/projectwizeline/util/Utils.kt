package com.example.projectwizeline.util

import android.app.Activity
import android.app.AlertDialog
import com.example.projectwizeline.R
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt


object Utils {

    private var dialog: AlertDialog? = null

    fun createLoadingDialog(activity: Activity) {
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.loading, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        builder.setCancelable(false)
        dialog = builder.create()
    }

    fun showLoadingDialog() {
        dialog?.show()
    }

    fun hideLoadingDialog() {
        dialog?.hide()
    }

    fun Double.toFormatCurrency(): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        format.currency = Currency.getInstance(Locale.getDefault())
        return format.format(this)
        //return format.format(this.roundToInt())
    }
}