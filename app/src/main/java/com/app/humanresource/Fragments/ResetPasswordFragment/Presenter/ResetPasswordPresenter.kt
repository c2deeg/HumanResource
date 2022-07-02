package com.app.humanresource.Fragments.ResetPasswordFragment.Presenter

import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.LoginActivity.LoginActivity
import com.app.humanresource.Fragments.ResetPasswordFragment.VIew.ResetPasswordView
import com.app.humanresource.Handler.ChangePasswordHandler
import com.app.humanresource.Models.ForogtoChangePasword.ChangePasswordExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class ResetPasswordPresenter(
    private val activity: FragmentActivity,
    private val resetPasswordView: ResetPasswordView
) {

    private var et_confirmpassword: String? = null
    private var et_newpassword: String? = null

    fun resetPasswordMethod(et_newpass: EditText?, et_confirmpass: EditText?) {
        var et_newpassword: String? = null
        var et_confirmpassword: String? = null
        et_newpassword = et_newpass?.text.toString()
        et_confirmpassword = et_confirmpass?.text.toString()
        this.et_newpassword = et_newpassword
        this.et_confirmpassword = et_confirmpassword


        var token = CSPreferences.readString(activity, Utils.FORGOTPASSWORDTOKEN)
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                resetPasswordView.showDialog(activity)
                WebServices.Factory1.getInstance()
                    ?.changePasswordMethod(token, et_newpassword!!, object : ChangePasswordHandler {
                        override fun onSuccess(changePasswordExample: ChangePasswordExample?) {
                            resetPasswordView.hideDialog()
                            if (changePasswordExample?.isSuccess == true) {
                                resetPasswordView.showMessage(activity, changePasswordExample.message)
                                var intent = Intent(activity, LoginActivity::class.java)
                                activity.startActivity(intent)
//                                activity.finish()
                            } else {
                                resetPasswordView.showMessage(
                                    activity,
                                    changePasswordExample?.message
                                )
                            }
                        }


                        override fun onError(message: String?) {
                            resetPasswordView.hideDialog()
                            resetPasswordView.showMessage(activity, message)
                        }

                    })

            } else {

            }
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }


    }

    private fun checkValidation(): Boolean {
        if (et_newpassword != et_confirmpassword) {
            Toast.makeText(activity, "Your Password Must Be Same", Toast.LENGTH_SHORT).show()
            return false
        } else if (et_newpassword?.length == 0) {
            Toast.makeText(activity, "Please Enter New Password", Toast.LENGTH_SHORT).show()
            return false
        }else if (et_confirmpassword?.length==0){
            Toast.makeText(activity, "Please Confirm Your Password", Toast.LENGTH_SHORT).show()
            return false

        }
        return true
    }
}