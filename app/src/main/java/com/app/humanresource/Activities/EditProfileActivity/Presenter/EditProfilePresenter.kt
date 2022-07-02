package com.app.humanresource.Activities.EditProfileActivity.Presenter

import android.graphics.Bitmap
import android.widget.Toast
import com.app.humanresource.Activities.EditProfileActivity.EditProfileActivity
import com.app.humanresource.Activities.EditProfileActivity.View.EditProfileView
import com.app.humanresource.Handler.EditProfileHandler
import com.app.humanresource.Handler.GetCurrentUserHandler
import com.app.humanresource.Handler.UpdateuserByidHandler
import com.app.humanresource.Models.EditProfile.UploadProfileIMageExample
import com.app.humanresource.Models.Profile.ProfileExample
import com.app.humanresource.Models.UpadateuserDataModels.UpdateUserExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.util.*

class EditProfilePresenter(
    private val activity: EditProfileActivity,
    private val editProfileView: EditProfileView
) {
    private var id: RequestBody? = null
    private var imagePart: MultipartBody.Part? = null

    fun uploadProfileImage(photo: Bitmap) {
        if (photo != null) {
            val stream = ByteArrayOutputStream()
            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream)
            val photoByteArray = stream.toByteArray()
            val requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), photoByteArray)
            id = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                CSPreferences.readString(activity, Utils.USERID)
            )
            val random = Random()
            imagePart = MultipartBody.Part.createFormData(
                "file",
                "abc" + random.nextInt(1000000) + ".jpg",
                requestFile
            )
        }
        if (Utils.isNetworkConnected(activity!!)) {
            editProfileView?.showDialog(activity)
            var idS: String? = null
            idS = CSPreferences.readString(activity, Utils.USERID)
            WebServices.Factory1.getInstance()?.uploadProfileImage(id!!, imagePart!!, object :
                EditProfileHandler {
                override fun onSuccess(profileExample: UploadProfileIMageExample?) {
                    editProfileView?.hideDialog()
                    if (profileExample != null) {
                        if (profileExample.getIsSuccess() === true) {
                            editProfileView?.showMessage(activity, profileExample.message)
//                            Utils.loginActivitychangeFragment(activity, ForgotNewPasswordFragment())
                        }
                    } else {
                        editProfileView?.showMessage(activity, profileExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    editProfileView?.hideDialog()
                    editProfileView?.showMessage(activity, message)
                }
            })
        }
    }

    fun updateuserInfo(name: String, phonenum: String, et_mail: String) {
        if (Utils.isNetworkConnected(activity!!)) {
            editProfileView?.showDialog(activity)
            var id: String? = null
            var Token: String? = null
            id = CSPreferences.readString(activity, Utils.USERID)
            Token = CSPreferences.readString(activity, Utils.TOKEN)
            WebServices.Factory1.getInstance()?.updateuserById(Token, id!!,name,"",phonenum,et_mail,object :
                UpdateuserByidHandler {
                override fun onSuccess(updateUserExample: UpdateUserExample?) {
                    editProfileView?.hideDialog()
                    if (updateUserExample != null) {
                        if (updateUserExample.getIsSuccess() === true) {
                            editProfileView?.showMessage(activity, updateUserExample.message)
                            activity.finish()
//                            Utils.loginActivitychangeFragment(activity, ForgotNewPasswordFragment())
                        }
                    } else {
                        editProfileView?.showMessage(activity, updateUserExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    editProfileView?.hideDialog()
                    editProfileView?.showMessage(activity, message)
                }
            })
        } else{
            Toast.makeText(activity,"Please Check Internet Connection",Toast.LENGTH_SHORT).show()
        }
    }
    //getuserdetail
    fun getCurrentUser() {
        var id: String? = null
        var token: String? = null
        token = CSPreferences.readString(activity!!, Utils.TOKEN)
        id = CSPreferences.readString(activity!!, Utils.USERID)
        if (Utils.isNetworkConnected(activity)) {
            editProfileView.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.getCurrentUser(token!!, id!!, object : GetCurrentUserHandler {
                    override fun onSuccess(getUserExample: ProfileExample?) {
                        editProfileView.hideDialog()
                        if (getUserExample?.isSuccess == true) {
                            editProfileView.getData(activity, getUserExample.data)
//                            CSPreferences.putString(activity,Utils.USERNAME,getUserExample.data.userName)
//                            CSPreferences.putString(activity,Utils.CONTACTNUMBER,getUserExample.data.phoneNumber)
//                            CSPreferences.putString(activity,Utils.USEREMAIL,getUserExample.data.email)


                        }
                    }

                    override fun onError(message: String?) {
                        editProfileView.hideDialog()
                        editProfileView.showMessage(activity, message)
                    }

                })

        }else{
            editProfileView.hideDialog()
            Toast.makeText(activity,"Please Check Internet Connection",Toast.LENGTH_SHORT).show()
        }
    }
}