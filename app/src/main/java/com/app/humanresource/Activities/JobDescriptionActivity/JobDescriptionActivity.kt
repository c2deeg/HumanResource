package com.app.humanresource.Activities.JobDescriptionActivity

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.humanresource.Activities.CompanyDetailsActivity.CompanyDetailsActivity
import com.app.humanresource.Activities.HomeActivity.HomeActivity
import com.app.humanresource.Activities.LoginActivity.LoginActivity
import com.app.humanresource.R
import com.hbb20.CountryCodePicker
import org.w3c.dom.Text
import java.io.File

class JobDescriptionActivity : AppCompatActivity(), View.OnClickListener {
    var activity: Activity? = null
    var tv_companyDetails: TextView? = null
    var tv_jobdeatils: TextView? = null
    var tv_jobdescription: TextView? = null
    var img_uploadfile: ImageView? = null
    var tv_uploadhere: TextView? = null
    var et_title: String? = null
    var et_category: String? = null
    var et_citylocation: String? = null
    var et_pricefrom: String? = null
    var et_priceto: String? = null
    var et_numofworkers: String? = null
    var et_firstname: EditText? = null
    var et_lastname: EditText? = null
    var et_scopework: EditText? = null
    var et_plainofaction: EditText? = null
    var et_personmail: EditText? = null
    var countrycodepicker: CountryCodePicker? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_description)
        activity = this
        init()
        listener()

        et_title = intent.getStringExtra("et_title")
        et_category = intent.getStringExtra("et_category")
        et_citylocation = intent.getStringExtra("et_citylocation")
        et_pricefrom = intent.getStringExtra("et_pricefrom")
        et_priceto = intent.getStringExtra("et_priceto")
        et_numofworkers = intent.getStringExtra("et_numofworkers")



    }

    private fun init() {
        tv_companyDetails = findViewById(R.id.tv_companyDetails)
        tv_jobdeatils = findViewById(R.id.tv_jobdeatils)
        tv_jobdescription = findViewById(R.id.tv_jobdescription)
        tv_uploadhere = findViewById(R.id.tv_uploadhere)
        img_uploadfile = findViewById(R.id.img_uploadfile)
        et_firstname = findViewById(R.id.et_firstname)
        et_lastname = findViewById(R.id.et_lastname)
        et_scopework = findViewById(R.id.et_scopework)
        et_plainofaction = findViewById(R.id.et_plainofaction)
        countrycodepicker = findViewById(R.id.countrycodepicker)
        et_personmail = findViewById(R.id.et_personmail)
//        Toast.makeText(activity,countrycodepicker?.selectedCountryName,Toast.LENGTH_SHORT).show()
////        countrycodepicker?.selectedCountryName

    }

    private fun listener() {
        tv_companyDetails?.setOnClickListener(this)
        tv_jobdeatils?.setOnClickListener(this)
        tv_jobdeatils?.setOnClickListener(this)
        img_uploadfile?.setOnClickListener(this)
//        countrycodepicker!!.setOnCountryChangeListener{
//            var countryname:String = countrycodepicker?.textView_selectedCountry.toString()
//            Toast.makeText(activity,countryname,Toast.LENGTH_SHORT).show()
//        }


    }

    override fun onClick(p0: View?) {
        if (p0 == tv_companyDetails) {
            if (et_firstname?.text?.length == 0 || et_lastname?.text?.length == 0 ||
                et_scopework?.text?.length == 0 || et_plainofaction?.text?.length == 0
            ) {
                validation()


            } else {
                tv_jobdeatils?.setTextColor(Color.WHITE)
                tv_jobdescription?.setTextColor(Color.WHITE)
                tv_companyDetails?.setTextColor(Color.parseColor("#EECB4F"))
                var inent = Intent(activity as FragmentActivity?, CompanyDetailsActivity::class.java)
                inent.putExtra("et_title",et_title)
                inent.putExtra("et_category",et_category)
                inent.putExtra("et_citylocation",et_citylocation)
                inent.putExtra("et_pricefrom",et_pricefrom)
                inent.putExtra("et_priceto",et_priceto)
                inent.putExtra("et_numofworkers",et_numofworkers)
                inent.putExtra("et_firstname",et_firstname?.text.toString())
                inent.putExtra("et_lastname",et_firstname?.text.toString())
                inent.putExtra("et_personmail",et_personmail?.text.toString())
                inent.putExtra("et_scopework",et_scopework?.text.toString())
                inent.putExtra("et_plainofaction",et_plainofaction?.text.toString())
                startActivity(inent)
            }
        } else if (p0 == tv_jobdeatils) {
            tv_jobdescription?.setTextColor(Color.WHITE)
            tv_companyDetails?.setTextColor(Color.WHITE)
            tv_jobdeatils?.setTextColor(Color.parseColor("#EECB4F"))
            var intent = Intent(activity, HomeActivity::class.java)
            intent.putExtra("role", "employer")
            startActivity(intent)
        } else if (p0 == img_uploadfile) {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "application/pdf"
            startActivityForResult(intent, 1212)
        }

    }

    fun validation() {
        if (et_firstname?.text?.length == 0) {
            Toast.makeText(activity, "Please enter first name", Toast.LENGTH_SHORT).show()
        } else if (et_lastname?.text?.length == 0) {
            Toast.makeText(activity, "Please enter last name", Toast.LENGTH_SHORT).show()
        } else if (countrycodepicker?.fullNumber?.length == 0) {
            Toast.makeText(activity, "Please select country", Toast.LENGTH_SHORT).show()
        } else if (et_scopework?.text?.length == 0) {
            Toast.makeText(activity, "Please enter scope of work", Toast.LENGTH_SHORT).show()

        } else if (et_plainofaction?.text?.length == 0) {
            Toast.makeText(activity, "Please enter plain of action", Toast.LENGTH_SHORT).show()

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1212 -> if (resultCode == RESULT_OK) {

                var path: String = data?.data?.path.toString()
                var path1: String = data?.data?.lastPathSegment.toString()
                tv_uploadhere?.text = path1
                // Get the Uri of the selected file
//                val uri: Uri? = data?.data
//                val uriString: String = uri.toString()
//                val myFile = File(uriString)
//                val path: String = myFile.getAbsolutePath()
//
//                displayName = myFile.name
//                if (uriString.startsWith("content://")) {
//                    var cursor: Cursor? = null
//                    try {
//                        cursor =
//                            activity?.getContentResolver()?.query(uri!!, null, null, null, null)
//                        if (cursor != null && cursor.moveToFirst()) {
//                            displayName =
//                                cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                            Toast.makeText(activity,displayName, Toast.LENGTH_SHORT).show()
//
//                        }
//                    } finally {
//                        cursor?.close()
//                    }
//                } else if (uriString.startsWith("file://")) {
//                    displayName = myFile.name
//                    var nme:String?=null
//                    nme =myFile.nameWithoutExtension
//                    Toast.makeText(activity,nme, Toast.LENGTH_SHORT).show()
//                    val bmImg = BitmapFactory.decodeFile(path)
//                   tv_uploadprofile?.text = displayName.toString()
//
////                    tv_uploadprofile?.text = displayName
//                    Toast.makeText(activity,displayName, Toast.LENGTH_SHORT).show()
//                    img_uploadfile?.setImageResource(R.drawable.imggirl3)
//                }
//            }
//        }

                super.onActivityResult(requestCode, resultCode, data)
            }


        }
    }


}