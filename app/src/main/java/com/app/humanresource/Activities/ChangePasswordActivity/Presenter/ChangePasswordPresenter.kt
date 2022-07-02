package com.app.humanresource.Activities.ChangePasswordActivity.Presenter

import android.widget.EditText
import android.widget.Toast
import com.app.humanresource.Activities.ChangePasswordActivity.ResetChangePasswordActivity
import com.app.humanresource.Activities.ChangePasswordActivity.VIew.ResetChangePasswordView
import com.app.humanresource.Handler.ChangeResetPasswordHandler
import com.app.humanresource.Models.ChangePassword.ResetChangePasswordExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class ChangePasswordPresenter(
    private val activity: ResetChangePasswordActivity
    ,
    private val changePasswordView: ResetChangePasswordView
) {
    private var et_confirmpassword: String? = null
    private var et_newpassword: String? = null

    fun ChangePassword(et_newpass: EditText?, et_confirmpass: EditText?) {
        var et_newpassword: String? = null
        var et_confirmpassword: String? = null
        et_newpassword = et_newpass?.text.toString()
        et_confirmpassword = et_confirmpass?.text.toString()
        this.et_newpassword = et_newpassword
        this.et_confirmpassword = et_confirmpassword
        var token:String?=null
        var id:String?=null
        token = CSPreferences.readString(activity,Utils.TOKEN)
        id = CSPreferences.readString(activity,Utils.USERID)
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation())
            changePasswordView?.showDialog(activity)
            WebServices.Factory1.getInstance()!!.changeResetPasswordMethod(
                token,
                id!!,
                et_newpassword,
                et_confirmpassword,
                object : ChangeResetPasswordHandler {
                    override fun onSuccess(changeResetPasswordExample: ResetChangePasswordExample?) {
                        if (changeResetPasswordExample?.isSuccess == true) {
                            changePasswordView?.hideDialog()

                            changePasswordView.showMessage(
                                activity,
                                changeResetPasswordExample.message
                            )
                            activity.finish()

                        } else {
                            changePasswordView.showMessage(
                                activity,
                                changeResetPasswordExample?.message
                            )
                        }
                    }

                    override fun onError(message: String?) {
                        changePasswordView?.hideDialog()
                        changePasswordView.showMessage(activity, message)
                    }

                })
        }
        else{
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