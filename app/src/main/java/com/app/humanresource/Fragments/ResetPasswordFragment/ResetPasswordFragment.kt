package com.app.humanresource.Fragments.ResetPasswordFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

    }

    private fun listener(view: View?) {
        btn_resetpassword?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == btn_resetpassword) {
            var pass:String
            pass = et_pass?.text.toString()
            resetPasswordPresenter!!.resetPasswordMethod(et_pass,et_confirmpass)

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