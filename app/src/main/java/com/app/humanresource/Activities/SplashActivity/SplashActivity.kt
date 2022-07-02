package com.app.humanresource.Activities.SplashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.humanresource.Activities.HomeActivity.HomeActivity
import com.app.humanresource.Activities.LetsGetStartedActivity.LetsGetStartedActivity
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */

            var status: String ?=null
           status = CSPreferences.readString(this, Utils.USERLOGIN)
            if (status.equals("true")) {
//                    Intent intent2 = new Intent(activity, HomeActivity.class);
                val intent2 = Intent(this, HomeActivity::class.java)
                intent2.putExtra("Setting", "")
                startActivity(intent2)
                finishAffinity()
            } else {
                val intent2 = Intent(this, LetsGetStartedActivity::class.java)
                startActivity(intent2)
            }


//            val mainIntent = Intent(this, SplashSkipActivity::class.java)
//            startActivity(mainIntent)
//            finish()
        }, 3000)

    }
}