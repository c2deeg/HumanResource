package com.app.humanresource.Activities.AllJobsDetailActivity.VIew

import android.app.Activity
import com.app.humanresource.Activities.AllJobsDetailActivity.AllJobsDetailActivity
import com.app.humanresource.Models.GetJobsModels.GetJobsDatum
import com.app.humanresource.Models.RecentPostModels.RecentPostsMessage

interface AllJobsDetailView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun setrelevantData(activity: AllJobsDetailActivity, data: List<GetJobsDatum>)
    fun setrecentData(activity: AllJobsDetailActivity, message: List<RecentPostsMessage>)
}