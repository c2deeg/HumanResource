package com.app.humanresource.Fragments.ResetPasswordFragment.VIew

import android.app.Activity

interface ResetPasswordView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}