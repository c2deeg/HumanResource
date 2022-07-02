package com.app.humanresource.Activities.EmployerWelcomeActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.app.humanresource.Activities.LoginActivity.LoginActivity
import com.app.humanresource.R

class EmployerWelcomeActivity : AppCompatActivity(), View.OnClickListener {
    var activity:Activity?=null
    var tv_postjob:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_welcome)
        activity = this
        init()
        listeners()
    }

    private fun init(){
        tv_postjob = findViewById(R.id.tv_postjob)

    }
    private  fun listeners(){
        tv_postjob?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v==tv_postjob){
            var intent  = Intent(activity,LoginActivity::class.java)
            intent.putExtra("role", "employer")
            startActivity(intent)

        }
    }
}