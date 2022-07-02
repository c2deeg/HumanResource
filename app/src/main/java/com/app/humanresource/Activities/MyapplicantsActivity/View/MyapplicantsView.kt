package com.app.humanresource.Activities.MyapplicantsActivity.View

import android.app.Activity

interface MyapplicantsView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}