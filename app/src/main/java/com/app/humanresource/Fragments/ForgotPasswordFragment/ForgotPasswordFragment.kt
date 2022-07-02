package com.app.humanresource.Fragments.ForgotPasswordFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Fragments.ForgotPasswordFragment.Presenter.ForgotPasswordPresenter
import com.app.humanresource.Fragments.ForgotPasswordFragment.View.ForgotPasswordView
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils


class ForgotPasswordFragment : Fragment(), View.OnClickListener, ForgotPasswordView {
    var activity: Activity? = null
    var btn_submit: Button? = null
    var et_mail: EditText? = null
    var forgotpasswordPresenter: ForgotPasswordPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        activity = getActivity()
        init(view)
        listeners(view)

        forgotpasswordPresenter = ForgotPasswordPresenter(activity as FragmentActivity?, this)
        return view
    }


    private fun init(view: View) {
        btn_submit = view.findViewById(R.id.btn_submit)
        et_mail = view.findViewById(R.id.et_mail)

    }

    private fun listeners(view: View?) {

        btn_submit?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == btn_submit) {
            forgotpasswordPresenter?.forgotpasswordMethod(et_mail)


        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, requireActivity()!!.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }


}