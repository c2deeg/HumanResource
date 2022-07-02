package com.app.humanresource.Utils

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class MyApplication: MultiDexApplication()  {
    override fun onCreate() {
        super.onCreate()
        WebServices().create()
        MultiDex.install(applicationContext)
//        FacebookSdk.sdkInitialize(getApplicationContext());
    }

}