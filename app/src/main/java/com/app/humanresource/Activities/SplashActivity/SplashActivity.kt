package com.app.humanresource.Activities.SplashActivity

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.humanresource.Activities.HomeActivity.HomeActivity
import com.app.humanresource.Activities.LetsGetStartedActivity.LetsGetStartedActivity
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        try {
            val info: PackageInfo = this.getPackageManager().getPackageInfo(
                "com.app.amigo",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d(
                    "KeyHash", "KeyHash:" + Base64.encodeToString(
                        md.digest(),
                        Base64.DEFAULT
                    )
                )
                Log.e("SHA1", md.toString())
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }

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