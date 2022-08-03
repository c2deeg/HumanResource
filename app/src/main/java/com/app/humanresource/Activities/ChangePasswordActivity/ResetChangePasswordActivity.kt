package com.app.humanresource.Activities.ChangePasswordActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.app.humanresource.Activities.ChangePasswordActivity.Presenter.ChangePasswordPresenter
import com.app.humanresource.Activities.ChangePasswordActivity.VIew.ResetChangePasswordView
import com.app.humanresource.R

class ResetChangePasswordActivity : AppCompatActivity(), ResetChangePasswordView,
    View.OnClickListener {
    var activity: Activity? = null
    var et_newpass: EditText? = null
    var et_confirmpass: EditText? = null
    var btn_changepassword: Button? = null
    var img_eye: ImageView? = null
    var img_eye2: ImageView? = null
    var flag = true

    var changePasswordPresenter: ChangePasswordPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        activity = this
        inits()
        listeners()
        changePasswordPresenter =
            ChangePasswordPresenter(activity as ResetChangePasswordActivity, this)
    }

    fun inits() {
        et_newpass = findViewById(R.id.et_newpass)
        et_confirmpass = findViewById(R.id.et_confirmpass)
        btn_changepassword = findViewById(R.id.btn_changepassword)
        img_eye = findViewById(R.id.img_eye)
        img_eye2 = findViewById(R.id.img_eye2)

    }

    fun listeners() {
        btn_changepassword?.setOnClickListener(this)
        img_eye?.setOnClickListener(this)
        img_eye2?.setOnClickListener(this)

    }

    override fun showMessage(activity: Activity?, msg: String?) {
        com.app.humanresource.Utils.Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        com.app.humanresource.Utils.Utils.showDialogMethod(activity, activity?.fragmentManager)
    }

    override fun hideDialog() {
        com.app.humanresource.Utils.Utils.hideDialog()
    }

    override fun onClick(p0: View?) {
        if (p0 == btn_changepassword) {
            changePasswordPresenter?.ChangePassword(et_newpass, et_confirmpass)
        } else if (p0 == img_eye) {
            if (flag) {
                img_eye!!.setImageResource(R.drawable.hidepasswords)
                if (et_newpass?.getTransformationMethod()?.javaClass?.getSimpleName() == "PasswordTransformationMethod") {
                    et_newpass?.setTransformationMethod(SingleLineTransformationMethod())
                    et_newpass?.setSelection(et_newpass?.text!!.length)

                } else {
                    et_newpass?.setTransformationMethod(PasswordTransformationMethod())
                    et_newpass?.setSelection(et_newpass?.text!!.length);
                }
            } else {
                img_eye!!.setImageResource(R.drawable.eye)


                et_newpass?.setTransformationMethod(PasswordTransformationMethod())
                et_newpass?.setSelection(et_newpass?.text!!.length);

            }
            flag = !flag
        } else if (p0 == img_eye2) {
            if (flag) {
                img_eye2!!.setImageResource(R.drawable.hidepasswords)
                if (et_confirmpass?.getTransformationMethod()?.javaClass?.getSimpleName() == "PasswordTransformationMethod") {
                    et_confirmpass?.setTransformationMethod(SingleLineTransformationMethod())
                    et_confirmpass?.setSelection(et_confirmpass?.text!!.length)

                } else {
                    et_confirmpass?.setTransformationMethod(PasswordTransformationMethod())
                    et_confirmpass?.setSelection(et_confirmpass?.text!!.length);
                }
            } else {
                img_eye2!!.setImageResource(R.drawable.eye)
                et_confirmpass?.setTransformationMethod(PasswordTransformationMethod())
                et_confirmpass?.setSelection(et_confirmpass?.text!!.length);

            }
            flag = !flag

        }
    }
}