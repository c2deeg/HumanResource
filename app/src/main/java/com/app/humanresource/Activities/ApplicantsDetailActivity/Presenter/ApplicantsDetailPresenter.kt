package com.app.humanresource.Activities.ApplicantsDetailActivity.Presenter

import android.util.Log
import android.widget.Toast
import com.app.humanresource.Activities.ApplicantsDetailActivity.ApplicantsDetailActivity
import com.app.humanresource.Activities.ApplicantsDetailActivity.View.ApplicantsDetailView
import com.app.humanresource.Handler.GetAppliedUserDetailHandler
import com.app.humanresource.Handler.GetJobApplicantsHandler
import com.app.humanresource.Models.GetApplieduserDetail.GetAppliedUserDetail
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicants
import com.app.humanresource.Utils.WebServices

class ApplicantsDetailPresenter(
    val activity: ApplicantsDetailActivity,
    val applicantsDetailView: ApplicantsDetailView,
    val jobid: String?,
    val userid: String?
) {

    fun getjobapplicants() {
        applicantsDetailView.showDialog(activity)
        WebServices.getInstance()?.getAppliedUserDetail(userid!!,jobid!!,object :GetAppliedUserDetailHandler{
            override fun onSuccess(getAppliedUserDetail: GetAppliedUserDetail?) {
                Log.d("checkuserid",userid)
                Log.d("checkjobid",jobid)
                if (getAppliedUserDetail?.isSuccess==true){
                    if (getAppliedUserDetail.data!=null){
                        applicantsDetailView.hideDialog()
                        applicantsDetailView.showMessage(activity,getAppliedUserDetail.message)
                        Toast.makeText(activity,"kkdkdk",Toast.LENGTH_LONG).show()
                        applicantsDetailView.setData(activity,getAppliedUserDetail.data)

                    }else{
                        applicantsDetailView.hideDialog()

                        Toast.makeText(activity,"fail",Toast.LENGTH_LONG).show()

                    }

                }else{
                    applicantsDetailView.showMessage(activity,getAppliedUserDetail?.message)

                }
            }

            override fun onError(message: String?) {
                applicantsDetailView.hideDialog()

                applicantsDetailView.showMessage(activity,message)
            }

        })
    }
}