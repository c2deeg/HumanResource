package com.app.humanresource.Activities.MyJobsActivity.View

import android.app.Activity

interface MyJobView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}