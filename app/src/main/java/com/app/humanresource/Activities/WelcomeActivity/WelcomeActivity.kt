package com.app.humanresource.Activities.WelcomeActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.app.humanresource.Activities.EmployerWelcomeActivity.EmployerWelcomeActivity
import com.app.humanresource.Activities.LoginActivity.LoginActivity
import com.app.humanresource.R

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {
    var tv_employer:TextView?=null
    var tv_employe:TextView?=null
    var activity:Activity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        activity = this
        inits()
        listeners()
    }



    private fun inits() {
        tv_employer = findViewById(R.id.tv_employer)
        tv_employe = findViewById(R.id.tv_employe)
    }

    private fun listeners() {
        tv_employer?.setOnClickListener(this)
        tv_employe?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v==tv_employer){
            tv_employer?.setTextColor(Color.WHITE)
            tv_employe?.setTextColor(Color.BLACK)
            var intent = Intent(activity,EmployerWelcomeActivity::class.java)
            startActivity(intent)
        } else if (v==tv_employe){
            tv_employer?.setTextColor(Color.BLACK)
            tv_employe?.setTextColor(Color.WHITE)
            var intent = Intent(activity,LoginActivity::class.java)
            intent.putExtra("role", "employee")
            startActivity(intent)
        }
    }


}