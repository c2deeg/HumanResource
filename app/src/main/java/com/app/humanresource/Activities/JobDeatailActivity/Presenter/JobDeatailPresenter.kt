package com.app.humanresource.Activities.JobDeatailActivity.Presenter

import android.widget.Toast
import com.app.humanresource.Activities.JobDeatailActivity.JobDeatailActivity
import com.app.humanresource.Activities.JobDeatailActivity.View.JobDeatailView
import com.app.humanresource.Handler.GetJobByIdHandler
import com.app.humanresource.Models.GetJobById.GetJobByIdExample
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class JobDeatailPresenter(
    private val activity: JobDeatailActivity,
    private val jobDeatailView: JobDeatailView
) {

    fun getJobsById(jobid: String?) {
        if (Utils.isNetworkConnected(activity!!)) {
            jobDeatailView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getJobById(jobid!!, object :
                GetJobByIdHandler {
                override fun onSuccess(getJobByIdExample: GetJobByIdExample?) {
                    jobDeatailView.hideDialog()
                    if (getJobByIdExample != null) {
                        if (getJobByIdExample?.isSuccess == true) {
                            jobDeatailView.showMessage(activity, getJobByIdExample.message)
                            jobDeatailView.setData(activity,getJobByIdExample.data)
                        }
                    }
                }

                override fun onError(message: String?) {
                    jobDeatailView.hideDialog()
                    jobDeatailView.showMessage(activity,message)

                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
}