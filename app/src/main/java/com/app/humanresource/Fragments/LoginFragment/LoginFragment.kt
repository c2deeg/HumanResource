import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Fragments.ForgotPasswordFragment.ForgotPasswordFragment
import com.app.humanresource.Fragments.LoginFragment.Presenter.LoginPresenter
import com.app.humanresource.Fragments.LoginFragment.View.LoginView
import com.app.humanresource.Fragments.SignUpFragment.SignUpFragment
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils
import com.facebook.CallbackManager
import com.facebook.CallbackManager.Factory.create
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginFragment : Fragment(), View.OnClickListener, LoginView {
    private lateinit var callbackManager: CallbackManager
    var tv_createaccount: TextView? = null
    var tv_forgotpassword: TextView? = null
    var activity: Activity? = null
    var btn_login: TextView? = null
    var img_eye: ImageView? = null
    var et_pass: EditText? = null
    var et_mail: EditText? = null
    var img_googlesignin: ImageView? = null
    lateinit var role: String
    var flag = true
    var loginPresenter: LoginPresenter? = null
    var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 1
    var googleusername: String? = null
    var googleusernamemail: String? = null
    var googleuserid: String? = null
    var googleuserphonenum: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        activity = getActivity()
        init(view)
        listeners(view)


//        callbackManager = create()
//
//        LoginManager.getInstance().registerCallback(callbackManager,
//            object : FacebookCallback<LoginResult?> {
//                fun onSuccess(loginResult: LoginResult) {
//                    // App code
//                }
//
//                override fun onCancel() {
//                    // App code
//                }
//
//                override fun onError(exception: FacebookException) {
//                    // App code
//                }
//            })

        role = requireArguments().getString("role")!!


        loginPresenter = LoginPresenter(activity as FragmentActivity, this, role)

        //googlesignin
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(activity as FragmentActivity, gso);
        return view
    }

    private fun init(view: View) {
        tv_createaccount = view.findViewById(R.id.tv_createaccount)
        tv_forgotpassword = view.findViewById(R.id.tv_forgotpassword)
        btn_login = view.findViewById(R.id.btn_login)
        img_eye = view.findViewById(R.id.img_eye)
        et_mail = view.findViewById(R.id.et_mail)
        et_pass = view.findViewById(R.id.et_pass)
        img_googlesignin = view.findViewById(R.id.img_googlesignin)
    }

    private fun listeners(view: View?) {
        tv_createaccount?.setOnClickListener(this)
        tv_forgotpassword?.setOnClickListener(this)
        btn_login?.setOnClickListener(this)
        img_eye?.setOnClickListener(this)
        img_googlesignin?.setOnClickListener(this)

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
            loginPresenter?.loginMethod(mail, pass)
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

        } else if (v == img_googlesignin) {
            signIn()
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


    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(activity as FragmentActivity)
//        updateUI(account)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            handleSignInResult(task)
        }
    }


    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            googleusername = account.displayName
            googleusernamemail = account.email
            googleuserid = account.id
            Log.d("soiald", googleuserid.toString())
            loginPresenter?.sociallogin(
                googleuserid!!,
                "google",
                googleusernamemail!!,
                googleusername!!,
                ""
            )


            // Signed in successfully, show authenticated UI.
//            Toast.makeText(activity, "success", Toast.LENGTH_LONG).show()
//            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(activity, "fail", Toast.LENGTH_LONG).show()

            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
//            updateUI(null)
        }

    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        callbackManager.onActivityResult(requestCode, resultCode, data)
//        super.onActivityResult(requestCode, resultCode, data)
//    }

//    fun movetonextscreen(){
//        if (role.equals("employee")){
//            var intent  = Intent(activity,HomeActivity::class.java)
//            intent.putExtra("role",role)
//            startActivity(intent)
//        }else{
//            var intent  = Intent(activity,HomeActivity::class.java)
//            startActivity(intent)
//        }
//
//
//    }


}