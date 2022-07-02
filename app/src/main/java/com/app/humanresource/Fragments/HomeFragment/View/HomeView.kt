package com.app.humanresource.Fragments.HomeFragment.View

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Models.GetAllLocationModels.GetallLocationDatum
import com.app.humanresource.Models.GetPopularjobsModels.PopularJobsMessage
import com.app.humanresource.Models.GetallCategoryExample.GetallCategoryDatum
import com.app.humanresource.Models.Profile.ProfileData
import com.app.humanresource.Models.RecentPostModels.RecentPostsMessage
import com.app.humanresource.Models.SearchModels.SearchMessage

interface HomeView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun setData(activity: FragmentActivity, data: List<GetallCategoryDatum>)
    fun setLocationData(activity: FragmentActivity, data: List<GetallLocationDatum>)
    fun setlistData(activity: FragmentActivity, message: List<RecentPostsMessage>)
    fun setPopularjobsData(activity: FragmentActivity, message: List<PopularJobsMessage>)
    fun getData(activity: FragmentActivity, data: ProfileData?)
}