import android.app.Activity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Fragments.ForgotPasswordFragment.ForgotPasswordFragment
import com.app.humanresource.Fragments.LoginFragment.Presenter.LoginPresenter
import com.app.humanresource.Fragments.LoginFragment.View.LoginView
import com.app.humanresource.Fragments.SignUpFragment.SignUpFragment
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils


class LoginFragment : Fragment(), View.OnClickListener, LoginView {
    var tv_createaccount: TextView? = null
    var tv_forgotpassword: TextView? = null
    var activity: Activity? = null
    var btn_login: TextView? = null
    var img_eye: ImageView? = null
    var et_pass: EditText? = null
    var et_mail: EditText? = null
    lateinit var role: String
    var flag = true
    var loginPresenter: LoginPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        activity = getActivity()
        init(view)
        listeners(view)

        role = requireArguments().getString("role")!!

        Log.d("rolecheck", role)
        loginPresenter = LoginPresenter(activity as FragmentActivity, this)



        return view
    }

    private fun init(view: View) {
        tv_createaccount = view.findViewById(R.id.tv_createaccount)
        tv_forgotpassword = view.findViewById(R.id.tv_forgotpassword)
        btn_login = view.findViewById(R.id.btn_login)
        img_eye = view.findViewById(R.id.img_eye)
        et_mail = view.findViewById(R.id.et_mail)
        et_pass = view.findViewById(R.id.et_pass)
    }

    private fun listeners(view: View?) {
        tv_createaccount?.setOnClickListener(this)
        tv_forgotpassword?.setOnClickListener(this)
        btn_login?.setOnClickListener(this)
        img_eye?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == tv_createaccount) {
            val bundle = Bundle()
            bundle.putString("role", role.toString())
            val fragment = SignUpFragment()
            fragment.setArguments(bundle);
            val fm = getActivity()?.supportFragmentManager
            val transaction = fm?.beginTransaction()
            transaction?.add(R.id.login_container, fragment)
            transaction?.commit()
//            Utils.loginActivitychangeFragment(activity as FragmentActivity,SignUpFragment())
        } else if (v == tv_forgotpassword) {
            Utils.loginActivitychangeFragment(
                activity as FragmentActivity,
                ForgotPasswordFragment()
            )
        } else if (v == btn_login) {
            var mail: String? = null
            var pass: String? = null
            mail = et_mail?.text.toString()
            pass = et_pass?.text.toString()
            loginPresenter?.loginMethod(mail, pass, role)
        } else if (v == img_eye) {
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