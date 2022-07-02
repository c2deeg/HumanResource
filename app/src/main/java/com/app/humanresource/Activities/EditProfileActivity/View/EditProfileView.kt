package com.app.humanresource.Activities.EditProfileActivity.View

import android.app.Activity
import com.app.humanresource.Activities.EditProfileActivity.EditProfileActivity
import com.app.humanresource.Models.Profile.ProfileData

interface EditProfileView {

    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun getData(activity: EditProfileActivity, data: ProfileData?)
}