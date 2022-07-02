package com.app.humanresource.Activities.ApplicantsDetailActivity.View

import android.app.Activity

interface ApplicantsDetailView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}