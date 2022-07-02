package com.app.humanresource.Fragments.ProfileFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Fragments.ProfileFragment.View.ProfileView
import com.app.humanresource.Handler.GetCurrentUserHandler
import com.app.humanresource.Models.Profile.ProfileExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class ProfilePresenter(
    private val activity: FragmentActivity?,
    private val profileView: ProfileView
) {


    fun getCurrentUser() {
        var id: String? = null
        var token: String? = null
        token = CSPreferences.readString(activity!!, Utils.TOKEN)
        id = CSPreferences.readString(activity!!, Utils.USERID)
        if (Utils.isNetworkConnected(activity)) {
            profileView.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.getCurrentUser(token!!, id!!, object : GetCurrentUserHandler {
                    override fun onSuccess(getUserExample: ProfileExample?) {
                        profileView.hideDialog()
                        if (getUserExample?.isSuccess == true) {
                            profileView.getData(activity, getUserExample.data)
                            CSPreferences.putString(activity,Utils.USERNAME,getUserExample.data.userName)
                            CSPreferences.putString(activity,Utils.CONTACTNUMBER,getUserExample.data.phoneNumber)
                            CSPreferences.putString(activity,Utils.USEREMAIL,getUserExample.data.email)


                        }
                    }

                    override fun onError(message: String?) {
                        profileView.hideDialog()
                        profileView.showMessage(activity, message)
                    }

                })

        }else{
            profileView.hideDialog()

            Toast.makeText(activity,"Please Check Internet Connection",Toast.LENGTH_SHORT).show()
        }
    }

}