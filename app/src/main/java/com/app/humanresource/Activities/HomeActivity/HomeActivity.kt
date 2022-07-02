package com.app.humanresource.Activities.HomeActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import com.app.humanresource.Fragments.EmployerHomeFragment.EmployerHomeFragment
import com.app.humanresource.Fragments.HomeFragment.HomeFragment
import com.app.humanresource.Fragments.ProfileFragment.ProfileFragment
import com.app.humanresource.Fragments.FavouriteFragment.FavouriteFragment
import com.app.humanresource.Fragments.chatFragment.chatFragment
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    var activity:Activity?=null
    var home_container:FrameLayout?=null
    var bottomnav:BottomNavigationView?=null
    lateinit var role:String
    var rolesharedpre:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        listeners()
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

                R.id.chat -> setCurrentFragment(chatFragment)
                R.id.setting -> setCurrentFragment(settingFragment)
                R.id.profile ->profilefragmnet()




            }
            true


        }


    }

    private fun init(){
        home_container = findViewById(R.id.home_container)

    }

    private fun listeners(){

    }
    override fun onBackPressed() {
        finishAffinity()
    }


    private fun setCurrentFragment(fragment: androidx.fragment.app.Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_container, fragment)
            commit()
        }

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
}