package com.app.humanresource.Activities.CompanyDetailsActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.app.humanresource.Activities.CompanyDetailsActivity.Presenter.CompanyDetailsPresenter
import com.app.humanresource.Activities.CompanyDetailsActivity.View.CompanyDetailsView
import com.app.humanresource.Activities.HomeActivity.HomeActivity
import com.app.humanresource.Activities.JobDescriptionActivity.JobDescriptionActivity
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils
import com.hbb20.CountryCodePicker

class CompanyDetailsActivity : AppCompatActivity(), View.OnClickListener, CompanyDetailsView,
    AdapterView.OnItemSelectedListener {
    var tv_jobdescription: TextView? = null
    var tv_companyDetails: TextView? = null
    var tv_details: TextView? = null
    var btn_postjob: Button? = null
    var activity: Activity? = null
    var et_title: String? = null
    var et_category: String? = null
    var et_citylocation: String? = null
    var et_pricefrom: String? = null
    var et_priceto: String? = null
    var et_numofworkers: String? = null
    var et_firstname: String? = null
    var et_lastname: String? = null
    var et_scopework: String? = null
    var et_plainofaction: String? = null
    var fullnumber: String? = null
    var countryname: String? = null
    var et_mail: EditText? = null
    var et_skills: EditText? = null
    var et_personmail: String? = null
    var mail: String? = null
    var adrees: String? = null
    var ccp: CountryCodePicker? = null
    var et_phonenum: EditText? = null
    var et_adress: EditText? = null
    var experiencerequired_spinner: Spinner? = null
    var mCountryChangedByUser = true
    private lateinit var companyarraylist: ArrayList<String>
    var list = ArrayList<String>() // define this is as globally
    var yearsofexperience = arrayOf("0-1 Years", "1-2 Years", "2-3 Years","4-5 Years")
    var spinnerexperienceText:String?=null



    var companyDetailsPresenter: CompanyDetailsPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_details)
        activity = this
        init()
        listeners()
        companyDetailsPresenter = CompanyDetailsPresenter(activity as CompanyDetailsActivity, this)
        et_title = intent.getStringExtra("et_title")
        et_category = intent.getStringExtra("et_category")
        et_citylocation = intent.getStringExtra("et_citylocation")
        et_pricefrom = intent.getStringExtra("et_pricefrom")
        et_priceto = intent.getStringExtra("et_priceto")
        et_numofworkers = intent.getStringExtra("et_numofworkers")
        et_firstname = intent.getStringExtra("et_firstname")
        et_lastname = intent.getStringExtra("et_lastname")
        et_personmail = intent.getStringExtra("et_personmail")
        et_scopework = intent.getStringExtra("et_scopework")
        et_plainofaction = intent.getStringExtra("et_plainofaction")

        //experience required spinner
        val experiencerequired_spinner = findViewById<Spinner>(R.id.experiencerequired_spinner)
        experiencerequired_spinner!!.setOnItemSelectedListener(this)
        val experiencerequired_spinnerAdapter = ArrayAdapter(activity as CompanyDetailsActivity, android.R.layout.simple_spinner_item, yearsofexperience)
        experiencerequired_spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        experiencerequired_spinner!!.setAdapter(experiencerequired_spinnerAdapter)
    }
    private fun init() {
        tv_jobdescription = findViewById(R.id.tv_jobdescription)
        tv_companyDetails = findViewById(R.id.tv_companyDetails)
        tv_details = findViewById(R.id.tv_details)
        btn_postjob = findViewById(R.id.btn_postjob)
        et_mail = findViewById(R.id.et_mail)
        ccp = findViewById(R.id.ccp)
        et_phonenum = findViewById(R.id.et_phonenum)
        et_adress = findViewById(R.id.et_adress)
        et_skills = findViewById(R.id.et_skills)

        //gettext
        mail = et_mail?.text.toString()
        adrees = et_adress?.text.toString()
        adrees = et_adress?.text.toString()
    }
    private fun listeners() {
        tv_jobdescription?.setOnClickListener(this)
        tv_details?.setOnClickListener(this)
        btn_postjob?.setOnClickListener(this)
        ccp!!.setOnCountryChangeListener {
           mCountryChangedByUser = true;
            fullnumber = ccp?.selectedCountryCode.toString() + et_phonenum?.text.toString()
            countryname = ccp?.selectedCountryEnglishName.toString()
        }
    }
    override fun onClick(v: View?) {
        if (v == tv_jobdescription) {
            tv_companyDetails?.setTextColor(Color.WHITE)
            tv_jobdescription?.setTextColor(Color.parseColor("#EECB4F"))
            var intent = Intent(activity, JobDescriptionActivity::class.java)
            startActivity(intent)

        } else if (v == tv_details) {
            tv_companyDetails?.setTextColor(Color.WHITE)
            tv_jobdescription?.setTextColor(Color.WHITE)
            tv_details?.setTextColor(Color.parseColor("#EECB4F"))
            var intent = Intent(activity, HomeActivity::class.java)
            intent.putExtra("role", "employer")
            startActivity(intent)
        } else if (v == btn_postjob) {
            companyDetailsPresenter!!.cretaejobMethod(
                et_title, et_category, et_citylocation, et_pricefrom, et_priceto, et_numofworkers,
                et_firstname, et_lastname, et_personmail, countryname.toString(), et_scopework, et_plainofaction, et_mail!!.text.toString(),
                fullnumber.toString(),et_adress!!.text.toString(),spinnerexperienceText.toString(),et_skills?.text.toString()
            )
        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//        Toast.makeText(activity, yearsofexperience[p2], Toast.LENGTH_SHORT).show()
        spinnerexperienceText = yearsofexperience[p2]

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}