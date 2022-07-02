package com.app.humanresource.Activities.ApplyJobActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.humanresource.R

import com.app.humanresource.Activities.ApplyJobActivity.Presenter.ApplyJobPresenter
import com.app.humanresource.Activities.ApplyJobActivity.View.ApplyJobView
import com.app.humanresource.Utils.Utils

import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.*
import com.hbb20.CountryCodePicker

import java.io.File
import java.io.FileOutputStream


class ApplyJobActivity : AppCompatActivity(), View.OnClickListener, ApplyJobView {
    private var pdfUri: Uri? = null
    var activity: Activity? = null
    var img_uploadfile: ImageView? = null
    var img_back: ImageView? = null
    var tv_uploadprofile: TextView? = null
    var displayName: String? = null
    var et_firstname: EditText? = null
    var et_lastname: EditText? = null
    var et_mail: EditText? = null
    var country:String?=null
    var jobid:String?=null
    var countrycodepicker: CountryCodePicker? = null
    var et_describeyourself: EditText? = null
    var btn_applynow: Button? = null
    var applJObPresenter: ApplyJobPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_job)
        activity = this
        init()
        listener()
        jobid = intent.getStringExtra("jobid")
        Toast.makeText(activity,jobid,Toast.LENGTH_SHORT).show()
        applJObPresenter = ApplyJobPresenter(activity as ApplyJobActivity, this)
    }
    private fun init() {
        img_uploadfile = findViewById(R.id.img_uploadfile)
        tv_uploadprofile = findViewById(R.id.tv_uploadprofile)
        img_back = findViewById(R.id.img_back)
        btn_applynow = findViewById(R.id.btn_applynow)
        et_firstname = findViewById(R.id.et_firstname)
        et_lastname = findViewById(R.id.et_lastname)
        et_mail = findViewById(R.id.et_mail)
        countrycodepicker = findViewById(R.id.countrycodepicker)
        et_describeyourself = findViewById(R.id.et_describeyourself)
    }
    private fun listener() {
        img_uploadfile?.setOnClickListener(this)
        img_back?.setOnClickListener(this)
        btn_applynow?.setOnClickListener(this)
        countrycodepicker?.setOnCountryChangeListener {
            country = countrycodepicker?.selectedCountryEnglishName


        }
    }

    override fun onClick(p0: View?) {
        if (p0 == img_uploadfile) {
            val pdfIntent = Intent(Intent.ACTION_GET_CONTENT)
            pdfIntent.type = "application/pdf"
            pdfIntent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(pdfIntent, 12)
        } else if (p0 == img_back) {
            finish()
        }else if(p0 ==btn_applynow){
            var firstname:String?=null
            var lastname:String?=null
            var email:String?=null
            var describe:String?=null
            firstname = et_firstname?.text.toString()
            lastname = et_lastname?.text.toString()
            email = et_mail?.text.toString()
            describe = et_describeyourself?.text.toString()

            applJObPresenter?.applyjob(jobid,firstname,lastname,email,country,describe)


        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_CANCELED) {
            // For loading PDF
            when (requestCode) {
                12 -> if (resultCode == RESULT_OK) {

                    pdfUri = data?.data!!
                    val uri: Uri = data?.data!!
                    val uriString: String = uri.toString()
                    var pdfName: String? = null
                    if (uriString.startsWith("content://")) {
                        var myCursor: Cursor? = null
                        try {
                            myCursor = applicationContext!!.contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null
                            )
                            if (myCursor != null && myCursor.moveToFirst()) {
                                pdfName =
                                    myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                                pdfTextView.text = pdfName
                                Toast.makeText(activity, pdfName, Toast.LENGTH_SHORT).show()
                            }

                            val file: File = getFile(pdfUri!!)
                            applJObPresenter!!.uploadPdfFile(file,jobid)
                            tv_uploadprofile?.text = pdfName

                        } finally {
                            myCursor?.close()
                        }
                    }
                }
            }
        }
    }

    fun getFile(documentUri: Uri): File {
        val inputStream = activity?.contentResolver?.openInputStream(documentUri)
        var file: File
        inputStream.use { input ->
            file =
                File(activity?.cacheDir, System.currentTimeMillis().toString() + ".pdf")
            FileOutputStream(file).use { output ->
                val buffer =
                    ByteArray(4 * 1024) // or other buffer size
                var read: Int = -1
                while (input?.read(buffer).also {
                        if (it != null) {
                            read = it
                        }
                    } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
        }
        return file
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, activity!!.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }
}