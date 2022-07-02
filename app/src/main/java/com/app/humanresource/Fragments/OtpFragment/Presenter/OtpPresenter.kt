package com.app.humanresource.Fragments.OtpFragment.Presenter

import android.app.Activity
import com.app.humanresource.Fragments.ForgotPasswordFragment.ForgotPasswordFragment
import com.app.humanresource.Fragments.OtpFragment.View.OtpView
import com.app.humanresource.Fragments.ResetPasswordFragment.ResetPasswordFragment
import com.app.humanresource.Handler.OtpVerificationHandler
import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.Models.Otp.OtpExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class OtpPresenter(private val activity: Activity, private val otpView: OtpView) {

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

    private fun checkValidation(): Boolean {
        if (otp?.length==0) {
            return false
        }

        return true
    }


}