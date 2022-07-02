package com.app.humanresource.Fragments.FavouriteFragment.View

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsDatum

interface FavouriteView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun setData(activity: FragmentActivity, data: List<GetFavjobsDatum>)
}