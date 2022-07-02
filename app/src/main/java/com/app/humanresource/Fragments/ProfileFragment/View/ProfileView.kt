package com.app.humanresource.Fragments.ProfileFragment.View

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Models.Profile.ProfileData

interface ProfileView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun getData(activity: FragmentActivity, data: ProfileData?)

}