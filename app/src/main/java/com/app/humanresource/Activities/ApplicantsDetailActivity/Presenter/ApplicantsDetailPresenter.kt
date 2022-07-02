package com.app.humanresource.Activities.ApplicantsDetailActivity.Presenter

import androidx.recyclerview.widget.LinearLayoutManager
import com.app.humanresource.Activities.ApplicantsDetailActivity.ApplicantsDetailActivity
import com.app.humanresource.Activities.ApplicantsDetailActivity.View.ApplicantsDetailView
import com.app.humanresource.Activities.MyapplicantsActivity.MyapplicantsActivity
import com.app.humanresource.Adapter.ApplicantsRecyclerAdapter
import com.app.humanresource.Handler.GetJobApplicantsHandler
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicants
import com.app.humanresource.Utils.WebServices

class ApplicantsDetailPresenter(
     val activity: ApplicantsDetailActivity,
     val applicantsDetailView: ApplicantsDetailView
) {

    fun getjobapplicants() {
        applicantsDetailView.showDialog(activity)
        WebServices.getInstance()?.getjobApplicants(jobid!!, object :
            GetJobApplicantsHandler {
            override fun onSuccess(getJobApplicants: GetJobApplicants?) {
                if (getJobApplicants?.isSuccess == true) {
                    applicantsDetailView.showMessage(activity, getJobApplicants.message)
                 
                } else {
                    applicantsDetailView.showMessage(activity, getJobApplicants?.message)
                }
            }

            override fun onError(message: String?) {
                applicantsDetailView.showMessage(activity, message)
            }


        })
    }
}