package com.app.humanresource.Fragments.SignUpFragment.Presenter

import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.LoginActivity.LoginActivity
import com.app.humanresource.Fragments.SignUpFragment.VIew.SignUpView
import com.app.humanresource.Handler.SignUpHandler
import com.app.humanresource.Models.SignUp.SignUpExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class SignUpPresenter(private val activity: FragmentActivity, private val signUpView: SignUpView) {


    private var et_username: EditText? = null
    private var et_mail: EditText? = null
    private var et_cpmpname: EditText? = null
    private var et_pass: EditText? = null
    private var role: String? = null
    private var et_phonenum: EditText? = null

    fun signupMethod(
        et_username: EditText?,
        et_mail: EditText?,
        et_cpmpname: EditText?,
        et_pass: EditText?,
        role: String,
        et_phonenum: EditText?
    ) {
        this.et_username = et_username
        this.et_mail = et_mail
        this.et_cpmpname = et_cpmpname
        this.et_pass = et_pass
        this.role = role
        this.et_phonenum = et_phonenum


        if (Utils.isNetworkConnected(activity)) {
            signUpView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.signupMethod(
                et_username?.text?.trim().toString(),
                et_mail?.text?.trim().toString(),
                et_cpmpname?.text?.trim().toString(),
                et_pass?.text?.trim().toString(),
                role, et_phonenum?.text?.trim().toString(),
                object : SignUpHandler {
                    override fun onSuccess(signUpExample: SignUpExample?, acesstoken: String?) {
                        signUpView?.hideDialog()
                        if (signUpExample != null) {
                            if (signUpExample.getIsSuccess() === true) {

                                CSPreferences.putString(activity!!, Utils.TOKEN, acesstoken)
                                Log.d("Checktoken", acesstoken + "")
                                signUpView?.showMessage(activity, signUpExample.getMessage())
                                var intent = Intent(activity, LoginActivity::class.java)
                                activity.startActivity(intent)

                            } else {
                                signUpView?.showMessage(activity, signUpExample.getMessage())
                            }
                        }

                    }

                    override fun onError(message: String?) {
                        signUpView?.hideDialog()
                        signUpView?.showMessage(activity, message)
                    }
                })
        }else{
            Toast.makeText(activity,"Please Check Internet Connection",Toast.LENGTH_SHORT).show()
        }

    }

}