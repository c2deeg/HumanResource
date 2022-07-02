package com.app.humanresource.Activities.LoginActivity

import LoginFragment
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.humanresource.R

class LoginActivity : AppCompatActivity() {
    var login_container: FrameLayout? = null
    lateinit var role: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inits()

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