package com.app.humanresource.Activities.LetsGetStartedActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.app.humanresource.R
import com.app.humanresource.Activities.WelcomeActivity.WelcomeActivity

class LetsGetStartedActivity : AppCompatActivity(), View.OnClickListener {
    var btn_letsstarted:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_skip)
        inits()
        listeners()
    }

    private fun inits() {
        btn_letsstarted  = findViewById(R.id.btn_letsstarted)
    }

    private fun listeners() {
        btn_letsstarted?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v==btn_letsstarted){
            var intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}