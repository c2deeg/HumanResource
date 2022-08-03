package com.app.humanresource.Activities.EditProfileActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.app.humanresource.Activities.EditProfileActivity.Presenter.EditProfilePresenter
import com.app.humanresource.Activities.EditProfileActivity.View.EditProfileView
import com.app.humanresource.Models.Profile.ProfileData
import com.app.humanresource.R
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.bumptech.glide.Glide
import java.io.IOException

class EditProfileActivity : AppCompatActivity(), View.OnClickListener, EditProfileView{
    private var data: ProfileData?=null
    var activity:Activity?=null
    var img_back:ImageView?=null
    var img_selectimage:ImageView?=null
    var img_profile:ImageView?=null
    var et_contact:EditText?=null
    var et_mail:EditText?=null
    var et_name:EditText?=null
    var btn_savenow:Button?=null
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var bitmap: Bitmap? = null
    private var editProfilePresenter: EditProfilePresenter?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        activity = this
        init()
        listeners()

        editProfilePresenter = EditProfilePresenter(activity as EditProfileActivity,this)
        editProfilePresenter?.getCurrentUser()
         var sharedprename= CSPreferences.readString(activity as EditProfileActivity,Utils.USERNAME)
//        et_name?.setText(sharedprename)
//        et_mail?.setText(CSPreferences.readString(activity as EditProfileActivity,Utils.USEREMAIL))
//        et_contact?.setText(CSPreferences.readString(activity as EditProfileActivity,Utils.CONTACTNUMBER))


    }
    private fun init(){
        img_back = findViewById(R.id.img_back)
        btn_savenow = findViewById(R.id.btn_savenow)
        img_selectimage = findViewById(R.id.img_selectimage)
        img_profile = findViewById(R.id.img_profile)
        et_name = findViewById(R.id.et_name)
        et_contact = findViewById(R.id.et_contact)
        et_mail = findViewById(R.id.et_mail)

    }

    private fun listeners() {
        img_back?.setOnClickListener(this)
        btn_savenow?.setOnClickListener(this)
        img_selectimage?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0==img_back){
            finish()
        }else if(p0==btn_savenow){
            var name:String = et_name?.text.toString()
            var phonenum:String = et_contact?.text.toString()
            var et_mail:String = et_mail?.text.toString()
            editProfilePresenter?.updateuserInfo(name,phonenum,et_mail)
            finish()
        }else if(p0==img_selectimage){

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }






    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
//            img_profile?.setImageURI(imageUri)
            try {
                bitmap =
                    MediaStore.Images.Media.getBitmap(activity!!.contentResolver, imageUri)
                editProfilePresenter?.uploadProfileImage(bitmap!!)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            img_profile?.setImageURI(imageUri)
        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity,msg)
    }

    override fun showDialog(activity: Activity?) {
      Utils.showDialogMethod(activity,activity?.fragmentManager!!)
    }

    override fun hideDialog() {
       Utils.hideDialog()
    }

    override fun getData(activity: EditProfileActivity, data: ProfileData?) {
        this.data = data
        et_name?.setText(data?.userName)
        et_mail?.setText(data?.email)
        et_contact?.setText(data?.phoneNumber)
        if (data?.image !== null) {
            Glide.with(this)
                .load(data.image).placeholder(R.drawable.usericons2)
                .into(img_profile!!)
        } else {
            img_profile?.setImageResource(R.drawable.usericons)
        }
    }

}