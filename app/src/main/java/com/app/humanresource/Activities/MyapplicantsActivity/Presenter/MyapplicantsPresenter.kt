package com.app.humanresource.Activities.MyapplicantsActivity.Presenter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyapplicantsActivity.MyapplicantsActivity
import com.app.humanresource.Activities.MyapplicantsActivity.View.MyapplicantsView
import com.app.humanresource.Adapter.ApplicantsRecyclerAdapter
import com.app.humanresource.Handler.GetJobApplicantsHandler
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicants
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class MyapplicantsPresenter(
    private val activity: MyapplicantsActivity,
    private val myapplicantsView: MyapplicantsView,
    private val applicantsrecyclerview: RecyclerView?,
    private val jobid: String?
) {
    fun getjobapplicants() {
        myapplicantsView.showDialog(activity)
        WebServices.getInstance()?.getjobApplicants(jobid!!, object :
            GetJobApplicantsHandler {
            override fun onSuccess(getJobApplicants: GetJobApplicants?) {
                if (getJobApplicants?.isSuccess == true) {
                    myapplicantsView.showMessage(activity, getJobApplicants.message)
                    var applicantsRecyclerAdapter =
                        ApplicantsRecyclerAdapter(activity as MyapplicantsActivity,getJobApplicants.data)
                    applicantsrecyclerview?.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    applicantsrecyclerview?.adapter = applicantsRecyclerAdapter
                } else {
                    myapplicantsView.showMessage(activity, getJobApplicants?.message)
                }
            }

            override fun onError(message: String?) {
                myapplicantsView.showMessage(activity, message)
            }


        })
    }

}