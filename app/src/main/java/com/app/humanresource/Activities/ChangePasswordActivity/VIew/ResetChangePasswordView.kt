package com.app.humanresource.Activities.ChangePasswordActivity.VIew

import android.app.Activity

interface ResetChangePasswordView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
}