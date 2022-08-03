package com.app.humanresource.Fragments.ResetPasswordFragment

import android.app.Activity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Fragments.ResetPasswordFragment.Presenter.ResetPasswordPresenter
import com.app.humanresource.Fragments.ResetPasswordFragment.VIew.ResetPasswordView
import com.app.humanresource.R


class ResetPasswordFragment : Fragment(), View.OnClickListener, ResetPasswordView {
    var activity: Activity? = null
    var btn_resetpassword: TextView? = null
    var et_pass: EditText? = null
    var et_confirmpass: EditText? = null
    var img_eye: ImageView? = null
    var img_eye2: ImageView? = null
    var flag = true
    var resetPasswordPresenter: ResetPasswordPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_reset_password, container, false)
        activity = getActivity()
        init(view)
        listener(view)
        resetPasswordPresenter = ResetPasswordPresenter(activity as  FragmentActivity,this)
        return view
    }


    private fun init(view: View) {
        btn_resetpassword = view.findViewById(R.id.btn_resetpassword)
        et_pass = view.findViewById(R.id.et_pass)
        et_confirmpass = view.findViewById(R.id.et_confirmpass)
        img_eye = view.findViewById(R.id.img_eye)
        img_eye2 = view.findViewById(R.id.img_eye2)

    }

    private fun listener(view: View?) {
        btn_resetpassword?.setOnClickListener(this)
        img_eye?.setOnClickListener(this)
        img_eye2?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == btn_resetpassword) {
            var pass:String
            pass = et_pass?.text.toString()
            resetPasswordPresenter!!.resetPasswordMethod(et_pass,et_confirmpass)
        }else if(v==img_eye){
            if (flag) {
                img_eye!!.setImageResource(R.drawable.hidepasswords)
                if (et_pass?.getTransformationMethod()?.javaClass?.getSimpleName() == "PasswordTransformationMethod") {
                    et_pass?.setTransformationMethod(SingleLineTransformationMethod())
                    et_pass?.setSelection(et_pass?.text!!.length)

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

        }else if(v==img_eye2){
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