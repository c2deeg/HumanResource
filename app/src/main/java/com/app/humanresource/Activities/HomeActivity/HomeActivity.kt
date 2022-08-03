package com.app.humanresource.Activities.HomeActivity

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.humanresource.Fragments.EmployerHomeFragment.EmployerHomeFragment
import com.app.humanresource.Fragments.FavouriteFragment.FavouriteFragment
import com.app.humanresource.Fragments.HomeFragment.HomeFragment
import com.app.humanresource.Fragments.ProfileFragment.ProfileFragment
import com.app.humanresource.Fragments.chatFragment.chatFragment
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeActivity : AppCompatActivity(), View.OnClickListener {
    var activity:Activity?=null
    var home_container:FrameLayout?=null
    var bottomnav:BottomNavigationView?=null
    lateinit var role:String
    lateinit var fab:FloatingActionButton
    var rolesharedpre:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        listeners()
        bottomnav = findViewById(R.id.bottomnav)

        bottomnav?.setBackgroundDrawable(null)
        role = intent.getStringExtra("role").toString()
        Log.e("checksrols",role+"")

        val homeFragment = HomeFragment()
        var employerHomeFragment = EmployerHomeFragment()
        val chatFragment = chatFragment()
        val profileFragment = ProfileFragment()
        val settingFragment = FavouriteFragment()

          rolesharedpre  = CSPreferences.readString(this,Utils.ROLE)

//        if (!(CSPreferences.readString(activity,Utils.ROLE).equals("employer"))){
        if (rolesharedpre.equals("employee")){
            setCurrentFragment(homeFragment)

        } else{

            setCurrentFragment(employerHomeFragment)


        }


        bottomnav = findViewById(R.id.bottomnav)
        bottomnav?.setOnNavigationItemSelectedListener setOnNavigationItemSelectedListener@{
            when (it.itemId) {
                R.id.home ->
                    if (rolesharedpre.equals("employee")){
                        setCurrentFragment(homeFragment)
                    }else{
                        setCurrentFragment(employerHomeFragment)
                    }

//                R.id.chat -> setCurrentFragment(chatFragment)
//                R.id.setting -> setCurrentFragment(settingFragment)
                R.id.profile ->profilefragmnet()




            }
            true


        }


    }

    private fun init(){
        home_container = findViewById(R.id.home_container)
        fab = findViewById(R.id.fab)

    }

    private fun listeners(){
        fab.setOnClickListener(this)

    }
//    override fun onBackPrezssed() {
//
//        if (bottomnav?.getSelectedItemId() == R.id.home) {
//            finish();
//        } else {
//            bottomnav?.setSelectedItemId(R.id.home);
//        }
////        finishAffinity()
//    }



//    private fun setCurrentFragment2(fragment: androidx.fragment.app.Fragment) =
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.home_container, fragment)
//            commit()
//        }

    private fun profilefragmnet(){
        val bundle = Bundle()
        bundle.putString("role", rolesharedpre.toString())
        val fragment = ProfileFragment()
        val fm = supportFragmentManager
        fragment.setArguments(bundle);
        val transaction = fm.beginTransaction()
        transaction.add(R.id.home_container, fragment)
        transaction.commit()
    }


    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            if (bottomnav?.getSelectedItemId() == R.id.home) {
                finishAffinity()
        } else {
                bottomnav?.setSelectedItemId(R.id.home)
//            finishAffinity()
        }
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    override fun onClick(p0: View?) {
        if (p0==fab){
            val chatFragment = chatFragment()
            setCurrentFragment(chatFragment)
        }
    }



    private  fun setCurrentFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}