package com.app.humanresource.Activities.ChangePasswordActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.app.humanresource.Activities.ChangePasswordActivity.Presenter.ChangePasswordPresenter
import com.app.humanresource.Activities.ChangePasswordActivity.VIew.ResetChangePasswordView
import com.app.humanresource.R

class ResetChangePasswordActivity : AppCompatActivity(),ResetChangePasswordView, View.OnClickListener {
    var activity:Activity?=null
    var et_newpass:EditText?=null
    var et_confirmpass:EditText?=null
    var btn_changepassword:Button?=null
    var changePasswordPresenter:ChangePasswordPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        activity = this
        inits()
        listeners()
        changePasswordPresenter = ChangePasswordPresenter(activity as ResetChangePasswordActivity,this)
    }

    fun inits(){
        et_newpass= findViewById(R.id.et_newpass)
        et_confirmpass= findViewById(R.id.et_confirmpass)
        btn_changepassword= findViewById(R.id.btn_changepassword)

    }

    fun listeners(){
        btn_changepassword?.setOnClickListener(this)

    }

    override fun showMessage(activity: Activity?, msg: String?) {
        com.app.humanresource.Utils.Utils.showMessage(activity,msg)
    }

    override fun showDialog(activity: Activity?) {
        com.app.humanresource.Utils.Utils.showDialogMethod(activity,activity?.fragmentManager)
    }

    override fun hideDialog() {
       com.app.humanresource.Utils.Utils.hideDialog()
    }

    override fun onClick(p0: View?) {
        if (p0==btn_changepassword){
            changePasswordPresenter?.ChangePassword(et_newpass,et_confirmpass)
        }
    }
}