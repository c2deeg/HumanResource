package com.app.humanresource.Activities.JobDeatailActivity.View

import android.app.Activity
import com.app.humanresource.Activities.JobDeatailActivity.JobDeatailActivity
import com.app.humanresource.Models.GetJobById.GetJobByIdData

interface JobDeatailView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun setData(activity: JobDeatailActivity, data: GetJobByIdData?)
}