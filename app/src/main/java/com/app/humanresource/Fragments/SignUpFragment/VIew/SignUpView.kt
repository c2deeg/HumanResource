package com.app.humanresource.Fragments.SignUpFragment.VIew

import android.app.Activity

interface SignUpView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}