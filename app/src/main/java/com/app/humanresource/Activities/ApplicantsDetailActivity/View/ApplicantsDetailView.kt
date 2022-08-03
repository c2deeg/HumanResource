package com.app.humanresource.Activities.ApplicantsDetailActivity.View

import android.app.Activity
import com.app.humanresource.Activities.ApplicantsDetailActivity.ApplicantsDetailActivity
import com.app.humanresource.Models.GetApplieduserDetail.GetAppliedUserDetailData
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicantsApplyBy
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicantsData

interface ApplicantsDetailView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun setData(activity: ApplicantsDetailActivity, data: GetAppliedUserDetailData)
}