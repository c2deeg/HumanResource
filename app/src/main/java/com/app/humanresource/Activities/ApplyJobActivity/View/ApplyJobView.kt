package com.app.humanresource.Activities.ApplyJobActivity.View

import android.app.Activity

interface ApplyJobView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}