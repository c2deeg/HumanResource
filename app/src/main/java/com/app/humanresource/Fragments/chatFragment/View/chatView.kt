package com.app.humanresource.Fragments.chatFragment.View

import android.app.Activity

interface chatView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}