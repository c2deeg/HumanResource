package com.app.humanresource.Activities.SearchResultActivities.View

import android.app.Activity
import com.app.humanresource.Activities.SearchResultActivities.SearchResultActivity
import com.app.humanresource.Models.ApplyfilterModels.JobFilterDatum
import com.app.humanresource.Models.SearchModels.SearchMessage

interface SearchResultView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun getfilterdata(activity: SearchResultActivity, data: List<JobFilterDatum>)
    fun getsearchdata(activity: SearchResultActivity, message: List<SearchMessage>)

}