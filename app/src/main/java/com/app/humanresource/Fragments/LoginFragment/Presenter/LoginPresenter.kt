package com.app.humanresource.Fragments.LoginFragment.Presenter

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.HomeActivity.HomeActivity
import com.app.humanresource.Fragments.LoginFragment.View.LoginView
import com.app.humanresource.Handler.LoginHandler
import com.app.humanresource.Handler.SocialLoginHandler
import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.Models.SocialLogin.GoogleSignInModel
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class LoginPresenter(
    private val activity: FragmentActivity?,
    private val loginView: LoginView,
    private val role: String
) {
    private var et_mail: String? = null
    private var et_pass: String? = null


    fun loginMethod(et_mail: String, et_pass: String) {
        this.et_mail = et_mail
        this.et_pass = et_pass
        var token: String? = null
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
//
                            CSPreferences.putString(activity, Utils.USERID, loginExample.data.id)
                            loginView?.showMessage(activity, loginExample.getMessage())
                            Toast.makeText(activity,role.toString(),Toast.LENGTH_LONG).show()

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
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    private fun checkValidation(): Boolean {
        if (et_mail?.length == 0) {
            return false
        } else if (et_pass?.length == 0) {
            return false
        }
        return true
    }

    fun sociallogin(
        socialLinkId: String,
        platform: String,
        email: String,
        userName: String,
        phoneNumber: String,
    ) {

        if (Utils.isNetworkConnected(activity!!)) {
            loginView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.socialLogin(socialLinkId, platform, email, userName, phoneNumber, object :
                    SocialLoginHandler {
                    override fun onSuccess(googleSignInModel: GoogleSignInModel?, acesstoken: String?) {
                        if (!(acesstoken.equals("undefined"))){
                            if (googleSignInModel?.isSuccess == true) {
                                if (googleSignInModel != null) {
                                    CSPreferences.putString(activity, Utils.USERLOGIN, "true")
                                    CSPreferences.putString(activity, Utils.ROLE, role)
                                    CSPreferences.putString(activity, Utils.USERID, googleSignInModel.data._id)
                                    CSPreferences.putString(activity,Utils.TOKEN,acesstoken)

                                    loginView.hideDialog()
                                    loginView.showMessage(activity, googleSignInModel.message)
                                      if (this@LoginPresenter.role.equals("employer")) {
                                        var intent = Intent(activity as FragmentActivity, HomeActivity::class.java)
                                        intent.putExtra("role", "employer")
                                         activity.startActivity(intent)
                                    } else {
                                        var intent = Intent(activity as FragmentActivity, HomeActivity::class.java)
                                        activity.startActivity(intent)
                                    }
                                }
                            } else {
                                loginView.showMessage(activity, googleSignInModel?.message)

                            }
                        }else{
                            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
                            loginView.hideDialog()
                        }

                    }

                    override fun onError(message: String?) {
                        loginView.hideDialog()
                        loginView.showMessage(activity, message)

                    }

                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }


}