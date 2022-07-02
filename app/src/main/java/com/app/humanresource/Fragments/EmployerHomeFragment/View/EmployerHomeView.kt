package com.app.humanresource.Fragments.EmployerHomeFragment.View

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Models.Profile.ProfileData

interface EmployerHomeView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun getData(activity: FragmentActivity, data: ProfileData?)
}