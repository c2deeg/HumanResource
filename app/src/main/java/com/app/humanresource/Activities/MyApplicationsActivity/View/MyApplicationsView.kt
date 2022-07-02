package com.app.humanresource.Activities.MyApplicationsActivity.View

import android.app.Activity

interface MyApplicationsView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}