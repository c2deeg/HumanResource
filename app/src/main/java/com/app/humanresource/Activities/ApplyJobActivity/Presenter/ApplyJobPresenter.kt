package com.app.humanresource.Activities.ApplyJobActivity.Presenter

import android.widget.Toast
import com.app.humanresource.Activities.ApplyJobActivity.ApplyJobActivity
import com.app.humanresource.Activities.ApplyJobActivity.View.ApplyJobView
import com.app.humanresource.Handler.ApplyJobsHandler
import com.app.humanresource.Handler.UploadPdfHandler
import com.app.humanresource.Models.ApplyJob.ApplyjobExample
import com.app.humanresource.Models.ApplyJob.UploadPdf.UploadPdfExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class ApplyJobPresenter(
    private val activity: ApplyJobActivity,
    private val applyJobView: ApplyJobView
) {
    private var id: RequestBody? = null


    fun uploadPdfFile(path: File, jobid: String?) {
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/pdf"), path)
//        id = RequestBody.create(
//            MediaType.parse("multipart/form-data"),
//            CSPreferences.readString(activity, Utils.USERID)
//        )
        val fileToUpload = MultipartBody.Part.createFormData("file", path.getName(), requestBody)
        val filename: RequestBody =
            RequestBody.create(MediaType.parse("text/plain"), path.getName())
        applyJobView.showDialog(activity)
        WebServices.Factory1.getInstance()
            ?.uploadPdfFileMethod( fileToUpload,jobid!!, object : UploadPdfHandler {
                override fun onSuccess(uploadPdfExample: UploadPdfExample?) {
                    applyJobView.hideDialog()
                    if (uploadPdfExample != null) {
                        if (uploadPdfExample?.isSuccess == true) {
                            applyJobView.showMessage(activity, uploadPdfExample.message)
                        } else {
                            applyJobView.showMessage(activity, uploadPdfExample.message)
                        }
                    }
                }

                override fun onError(message: String?) {
                    applyJobView.hideDialog()
                    Toast.makeText(activity, "error", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun applyjob(
        jobid: String?,
        firstname: String,
        lastname: String,
        email: String,
        country: String?,
        describe: String
    ) {
        var userid:String?=null
        userid = CSPreferences.readString(activity,Utils.USERID)

        if (Utils.isNetworkConnected(activity!!)) {
            applyJobView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.applyJobsMethod(userid,jobid,firstname, lastname, email,  country, describe, "string","in-process", object :
                ApplyJobsHandler {
                override fun onSuccess(applyJobsExample: ApplyjobExample?) {
                    applyJobView?.hideDialog()
                    if (applyJobsExample != null) {
                        if (applyJobsExample?.isSuccess === true) {
                            applyJobView?.showMessage(activity, applyJobsExample.message)
                            activity.finish()
                        }
                    } else {
                        applyJobView?.showMessage(activity, applyJobsExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    applyJobView?.hideDialog()
                    applyJobView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show()
        }

    }
}