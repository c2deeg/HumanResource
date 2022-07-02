package com.app.humanresource.Fragments.EmployerHomeFragment.Presenter

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Fragments.EmployerHomeFragment.View.EmployerHomeView
import com.app.humanresource.Fragments.OtpFragment.OtpFragment
import com.app.humanresource.Handler.CreateCategoryHandler
import com.app.humanresource.Handler.ForgotPasswordHandler
import com.app.humanresource.Handler.GetCurrentUserHandler
import com.app.humanresource.Models.CreateCategoryModels.CreateCategoryExample
import com.app.humanresource.Models.ForogotPassword.ForgotPasswordExample
import com.app.humanresource.Models.Profile.ProfileExample
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class EmployerHomePresenter(
    private val activity: FragmentActivity,
    private val employerHomeView: EmployerHomeView
) {

    fun createCategoryAPI(category: String) {
        if (Utils.isNetworkConnected(activity!!))
            WebServices.Factory1.getInstance()
                ?.createCategoryMethod(category, object : CreateCategoryHandler {
                    override fun onSuccess(createCategoryExample: CreateCategoryExample?) {
                        if (createCategoryExample?.isSuccess == true) {
                            employerHomeView.showMessage(activity, createCategoryExample?.message)
                            CSPreferences.putString(
                                activity,
                                Utils.CATEGORYID,
                                createCategoryExample.data.id
                            )
                        } else {
                            employerHomeView.showMessage(activity, createCategoryExample?.message)
                        }
                    }
                    override fun onError(message: String?) {
                        employerHomeView.showMessage(activity, message)
                    }
                }
                )


    }

    //getprofileimage_______________________________________________________________________________
    fun getCurrentUser() {
        var id: String? = null
        var token: String? = null
        token = CSPreferences.readString(activity!!, Utils.TOKEN)
        id = CSPreferences.readString(activity!!, Utils.USERID)
        if (Utils.isNetworkConnected(activity)) {
            WebServices.Factory1.getInstance()
                ?.getCurrentUser(token!!, id!!, object : GetCurrentUserHandler {
                    override fun onSuccess(getUserExample: ProfileExample?) {
                        employerHomeView?.hideDialog()
                        if (getUserExample?.isSuccess == true) {
                            employerHomeView?.getData(activity, getUserExample.data)
                            CSPreferences.putString(activity,Utils.USERNAME,getUserExample.data.userName)
                            CSPreferences.putString(activity,Utils.CONTACTNUMBER,getUserExample.data.phoneNumber)
                            CSPreferences.putString(activity,Utils.USEREMAIL,getUserExample.data.email)
                        }
                    }
                    override fun onError(message: String?) {
                        employerHomeView?.hideDialog()
                        employerHomeView?.showMessage(activity, message)
                    }
                })
        }else{
            employerHomeView?.hideDialog()
            Toast.makeText(activity,"Please Check Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }



}