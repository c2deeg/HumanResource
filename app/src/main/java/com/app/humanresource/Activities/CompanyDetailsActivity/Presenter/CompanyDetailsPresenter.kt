package com.app.humanresource.Activities.CompanyDetailsActivity.Presenter

import android.content.Intent
import android.util.Log
import com.app.humanresource.Activities.CompanyDetailsActivity.CompanyDetailsActivity
import com.app.humanresource.Activities.CompanyDetailsActivity.View.CompanyDetailsView
import com.app.humanresource.Activities.HomeActivity.HomeActivity
import com.app.humanresource.Handler.CreatejobHandler
import com.app.humanresource.Models.Createjob.CreateobsExamples
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class CompanyDetailsPresenter(
    private val activity: CompanyDetailsActivity,
    private val companyDetailsView: CompanyDetailsView
) {
    fun cretaejobMethod(
        et_title: String?,
        et_category: String?,
        et_citylocation: String?,
        et_pricefrom: String?,
        et_priceto: String?,
        et_numofworkers: String?,
        et_firstname: String?,
        et_lastname: String?,
        et_personmail: String?,
        countryname: String,
        et_scopework: String?,
        et_plainofaction: String?,
        et_mail: String,
        fullnumber: String,
        et_adress: String,
        spinnerexperienceText: String,
        skills: String
    ) {
        var constructionDocumentation: String?=null
        var jobType: String?=null
        constructionDocumentation = "notsuitable"
        jobType = CSPreferences.readString(activity,Utils.BOOLEANVALUE)
        var categoryid: String? = CSPreferences.readString(activity,Utils.CATEGORYID)
        var userid:String?= CSPreferences.readString(activity,Utils.USERID)
        if (Utils.isNetworkConnected(activity)) {
            companyDetailsView?.showDialog(activity)
            WebServices.getInstance()?.CreateJobMethod(
                et_title,
                categoryid,
                userid,
                et_pricefrom!!,
                et_priceto!!,
                et_citylocation!!,
                et_numofworkers!!,
                jobType!!,
                et_firstname!!,
                et_lastname!!,
                et_personmail!!,
                countryname!!,
                et_scopework!!,
                et_plainofaction!!,
                constructionDocumentation,
                et_mail,
                fullnumber,
                et_adress,
                spinnerexperienceText,
                skills,
                object : CreatejobHandler {
                    override fun onSuccess(createjobExample: CreateobsExamples?) {
                        if (createjobExample != null) {
                            if (createjobExample?.isSuccess == true) {
                                companyDetailsView.showMessage(activity, createjobExample.message)
                                var intent = Intent(activity, HomeActivity::class.java)
                                intent.putExtra("role", "employer")
                                activity.startActivity(intent)
                            } else {
                                companyDetailsView.showMessage(activity, createjobExample.message)
                            }
                        }
                    }
                    override fun onError(message: String?) {
                        companyDetailsView.hideDialog()
                        companyDetailsView.showMessage(activity, message)
                        Log.d("chechmsg",message.toString())

                    }

                })

        }

    }
}