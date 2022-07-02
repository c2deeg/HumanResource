package com.app.humanresource.Fragments.LoginFragment.Presenter

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.HomeActivity.HomeActivity
import com.app.humanresource.Fragments.LoginFragment.View.LoginView
import com.app.humanresource.Handler.LoginHandler
import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class LoginPresenter(private val activity: FragmentActivity?, private val loginView: LoginView) {
    private var et_mail: String?=null
    private var et_pass: String?=null
    lateinit var role: String


    fun loginMethod(et_mail: String, et_pass: String, role: String) {
        this.et_mail = et_mail
        this.et_pass = et_pass
        this.role = role
        var token:String?=null
        if (Utils.isNetworkConnected(activity!!)) {

            if (checkValidation())
                loginView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.loginMethod(et_mail, et_pass, token, object :
                LoginHandler {
                override fun onSuccess(loginExample: LoginExample?, acesstoken: String?) {
                    loginView?.hideDialog()
                    if (loginExample != null) {
                        if (loginExample.getIsSuccess() === true) {
                            CSPreferences.putString(activity, Utils.USERLOGIN, "true")
                            CSPreferences.putString(activity, Utils.ROLE, role)


                            CSPreferences.putString(activity!!, Utils.TOKEN, acesstoken)
//                            Toast.makeText(activity, loginExample.data.id, Toast.LENGTH_SHORT)
//                                .show()
                            Log.d("Checktoken", acesstoken + "")
                            CSPreferences.putString(activity, Utils.USERID, loginExample.data.id)
                            loginView?.showMessage(activity, loginExample.getMessage())
                            if (this@LoginPresenter.role.equals("employer")) {
                                var intent =
                                    Intent(activity as FragmentActivity, HomeActivity::class.java)
                                intent.putExtra("role", "employer")
                                activity.startActivity(intent)
                            } else {
                                var intent =
                                    Intent(activity as FragmentActivity, HomeActivity::class.java)
                                activity.startActivity(intent)
                            }

                        } else {
                            loginView?.showMessage(activity, loginExample.getMessage())
                        }
                    } else {
                        loginView?.showMessage(activity, loginExample?.getMessage())
                    }
                }

                override fun onError(message: String?) {
                    loginView?.hideDialog()
                    loginView?.showMessage(activity, message)
                }
            })
        }
        else{
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }






    private fun checkValidation(): Boolean {
        if (et_mail?.length==0) {
            return false
        }else if(et_pass?.length==0){
            return false
        }
        return true
    }




}