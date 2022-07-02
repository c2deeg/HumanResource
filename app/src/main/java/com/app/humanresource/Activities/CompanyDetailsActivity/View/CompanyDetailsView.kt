package com.app.humanresource.Activities.CompanyDetailsActivity.View

import android.app.Activity

interface CompanyDetailsView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}