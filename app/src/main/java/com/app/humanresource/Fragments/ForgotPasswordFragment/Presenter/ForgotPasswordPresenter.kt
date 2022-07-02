package com.app.humanresource.Fragments.ForgotPasswordFragment.Presenter

import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Fragments.ForgotPasswordFragment.View.ForgotPasswordView
import com.app.humanresource.Fragments.OtpFragment.OtpFragment
import com.app.humanresource.Fragments.SignUpFragment.SignUpFragment
import com.app.humanresource.Handler.ForgotPasswordHandler
import com.app.humanresource.Models.ForogotPassword.ForgotPasswordExample
import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class ForgotPasswordPresenter(
    private val activity: FragmentActivity?,
    private val forgotPasswordView: ForgotPasswordView
) {

    private var et_email: EditText?=null

    fun forgotpasswordMethod(et_mail: EditText?) {
        this.et_email = et_mail
        if (Utils.isNetworkConnected(activity!!))
            if (checkValidation())


            forgotPasswordView.showDialog(activity)
        WebServices.Factory1.getInstance()
            ?.forgotPasswordMethod(et_mail?.text.toString(), object : ForgotPasswordHandler {
                override fun onSuccess(forgotPasswordExample: ForgotPasswordExample?) {
                    if (forgotPasswordExample?.isSuccess == true) {
                        forgotPasswordView.showMessage(activity, forgotPasswordExample?.message)
                        CSPreferences.putString(activity,Utils.FORGOTPASSWORDTOKEN,forgotPasswordExample.data.token)

                        forgotPasswordView.hideDialog()

                        val bundle = Bundle()
                        bundle.putString("email",et_mail?.text.toString())
                        val fragment = OtpFragment()
                        fragment.setArguments(bundle);
                        val fm = activity?.supportFragmentManager
                        val transaction = fm?.beginTransaction()
                        transaction?.replace(R.id.login_container, fragment)
                        transaction?.commit()

//                        Utils.loginActivitychangeFragment(
//                            activity as FragmentActivity,
//                            OtpFragment()
//                        )
                    } else {
                        forgotPasswordView.showMessage(activity, forgotPasswordExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    forgotPasswordView.showMessage(activity, message)
                    forgotPasswordView.hideDialog()
                }

            }
            )


    }


    private fun checkValidation(): Boolean {
        if (et_email?.text?.length==0) {
            forgotPasswordView.showMessage(activity, "Please enter your email")
            return false
        }

        return true
    }
}