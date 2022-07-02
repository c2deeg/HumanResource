package com.app.humanresource.Fragments.SignUpFragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.LoginActivity.LoginActivity
import com.app.humanresource.Fragments.SignUpFragment.Presenter.SignUpPresenter
import com.app.humanresource.Fragments.SignUpFragment.VIew.SignUpView
import com.app.humanresource.R

class SignUpFragment : Fragment(), View.OnClickListener, SignUpView {
    var tv_login: TextView? = null
    var activity: Activity? = null
    var et_cpmpname: EditText? = null
    var img_eye: ImageView? = null
    var et_pass: EditText? = null
    var et_username: EditText? = null
    var et_mail: EditText? = null
    var et_phonenum: EditText? = null
    var btn_createAccount: Button? = null
    lateinit var role: String
    var flag = true
    var signUpPresenter: SignUpPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        activity = getActivity()
        inits(view)
        listeners(view)
        role = requireArguments().getString("role")!!
        Log.d("checkingmate", role)
        if (role.equals("employer")) {
            et_cpmpname?.visibility = View.VISIBLE
        } else {
            et_cpmpname?.visibility = View.GONE
        }

        signUpPresenter = SignUpPresenter(activity as FragmentActivity, this)

        return view
    }

    private fun inits(view: View) {
        tv_login = view.findViewById(R.id.tv_login)
        et_cpmpname = view.findViewById(R.id.et_cpmpname)
        img_eye = view.findViewById(R.id.img_eye)
        et_pass = view.findViewById(R.id.et_pass)
        btn_createAccount = view.findViewById(R.id.btn_createAccount)
        et_username = view.findViewById(R.id.et_username)
        et_mail = view.findViewById(R.id.et_mail)
        et_phonenum = view.findViewById(R.id.et_phonenum)

    }
    private fun listeners(view: View) {
        tv_login?.setOnClickListener(this)
        img_eye?.setOnClickListener(this)
        btn_createAccount?.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if (v == tv_login) {
            var intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

        } else if (v == img_eye) {
            if (flag) {
                img_eye!!.setImageResource(R.drawable.hidepasswords)

                if (et_pass?.getTransformationMethod()?.javaClass?.getSimpleName() == "PasswordTransformationMethod") {
                    et_pass?.setTransformationMethod(SingleLineTransformationMethod())
                    et_pass?.setSelection(et_pass?.text!!.length);
                } else {
                    et_pass?.setTransformationMethod(PasswordTransformationMethod())
                    et_pass?.setSelection(et_pass?.text!!.length);
                }

            } else {
                img_eye!!.setImageResource(R.drawable.eye)
                et_pass?.setTransformationMethod(PasswordTransformationMethod())
                et_pass?.setSelection(et_pass?.text!!.length);

            }
            flag = !flag

        } else if (v == btn_createAccount) {

            signUpPresenter?.signupMethod(et_username,et_mail,et_cpmpname,et_pass,role,et_phonenum)


        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        com.app.humanresource.Utils.Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        com.app.humanresource.Utils.Utils.showDialogMethod(
            activity,
            requireActivity()!!.fragmentManager
        )
    }

    override fun hideDialog() {
        com.app.humanresource.Utils.Utils.hideDialog()
    }


}