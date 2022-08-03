package com.app.humanresource.Fragments.OtpFragment.Presenter

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import com.app.humanresource.Fragments.ForgotPasswordFragment.ForgotPasswordFragment
import com.app.humanresource.Fragments.OtpFragment.OtpFragment
import com.app.humanresource.Fragments.OtpFragment.View.OtpView
import com.app.humanresource.Fragments.ResetPasswordFragment.ResetPasswordFragment
import com.app.humanresource.Handler.ForgotPasswordHandler
import com.app.humanresource.Handler.OtpVerificationHandler
import com.app.humanresource.Models.ForogotPassword.ForgotPasswordExample
import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.Models.Otp.OtpExample
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class OtpPresenter(private val activity: Activity, private val otpView: OtpView) {

    private var et_email: String?=null
    private var otp: String?=null

    fun otpMethod(otp: String) {
        this.otp = otp
        var token = CSPreferences.readString(activity, Utils.FORGOTPASSWORDTOKEN)
        if (Utils.isNetworkConnected(activity))
            if (checkValidation())
            otpView.showDialog(activity)
        WebServices.Factory1.getInstance()
            ?.otpVerificationMethod(token, otp, object : OtpVerificationHandler {
                override fun onSuccess(otpExample: OtpExample?) {
                    otpView.hideDialog()
                    if (otpExample?.isSuccess == true) {
                        otpView.showMessage(activity,otpExample.message)
                        Utils.loginActivitychangeFragment(activity, ResetPasswordFragment())


                    } else {
                        otpView.showMessage(activity, otpExample?.message)
                    }

                }

                override fun onError(message: String?) {
                    otpView.hideDialog()
                    otpView.showMessage(activity, message)
                }


            }
            )
    }



    fun forgotpasswordMethod(et_mail: String?) {
        this.et_email = et_mail
        if (Utils.isNetworkConnected(activity!!))
            if (checkValidation2())


                otpView.showDialog(activity)
        WebServices.Factory1.getInstance()
            ?.forgotPasswordMethod(et_mail, object : ForgotPasswordHandler {
                override fun onSuccess(forgotPasswordExample: ForgotPasswordExample?) {
                    if (forgotPasswordExample?.isSuccess == true) {
                        otpView.showMessage(activity, forgotPasswordExample?.message)
                        CSPreferences.putString(activity,Utils.FORGOTPASSWORDTOKEN,forgotPasswordExample.data.token)

                        otpView.hideDialog()


                    } else {
                        otpView.showMessage(activity, forgotPasswordExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    otpView.showMessage(activity, message)
                    otpView.hideDialog()
                }

            }
            )


    }


    private fun checkValidation2(): Boolean {
        if (et_email?.length==0) {
            otpView.showMessage(activity, "Please enter your email")
            return false
        }

        return true
    }

    private fun checkValidation(): Boolean {
        if (otp?.length==0) {
            return false
        }

        return true
    }




}