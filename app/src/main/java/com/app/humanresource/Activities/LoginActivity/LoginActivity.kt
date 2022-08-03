package com.app.humanresource.Activities.LoginActivity

import LoginFragment
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.humanresource.R
import com.facebook.CallbackManager

class LoginActivity : AppCompatActivity() {
    private var callbackManager: CallbackManager?=null
    var login_container: FrameLayout? = null
    lateinit var role: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inits()
        callbackManager = CallbackManager.Factory.create();

        role = intent.getStringExtra("role").toString()
        Log.d("checking", role + "")

        val bundle = Bundle()
        bundle.putString("role", role.toString())
        val fragment = LoginFragment()
        val fm = supportFragmentManager
        fragment.setArguments(bundle);
        val transaction = fm.beginTransaction()
        transaction.add(R.id.login_container, fragment)
        transaction.commit()

    }

    private fun inits() {
        login_container = findViewById(R.id.login_container)
    }
//    override fun onBackPressed() {
//        finishAffinity()
//    }

}