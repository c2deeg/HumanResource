package com.app.humanresource.Fragments.OtpFragment.View

import android.app.Activity

interface OtpView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}