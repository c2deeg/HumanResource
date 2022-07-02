package com.app.humanresource.Activities.JobDeatailActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.humanresource.Activities.ApplyJobActivity.ApplyJobActivity
import com.app.humanresource.R
import android.util.Log
import android.widget.*
import com.app.humanresource.Activities.ChatDetailActivity.ChatDetailActivity
import com.app.humanresource.Activities.JobDeatailActivity.Presenter.JobDeatailPresenter
import com.app.humanresource.Activities.JobDeatailActivity.View.JobDeatailView
import com.app.humanresource.Models.GetJobById.GetJobByIdData
import com.app.humanresource.Utils.Utils


class JobDeatailActivity : AppCompatActivity(), View.OnClickListener,JobDeatailView{
    private var data: GetJobByIdData? = null
    var activity: Activity? = null
    var btn_applyjob: Button? = null
    var img_message: ImageView? = null
    var jobid:String?=null
    var tv_experiencetime:TextView?=null
    var tv_skills:TextView?=null
    var tv_category:TextView?=null
    var tv_city:TextView?=null
    var tv_country:TextView?=null
    var tv_jobtype:TextView?=null
    var tv_pricedetail:TextView?=null
    var tv_location:TextView?=null
    var tv_phonenumber:TextView?=null
    var tv_compadress:TextView?=null
    var tv_email:TextView?=null
    var tv_planofaction:TextView?=null
    var linear_description:LinearLayout?=null
    var tv_jobdescription:TextView?=null
    var tv_jobdetailsdescription:TextView?=null
    var linear_jobdescription:LinearLayout?=null
    var jobsDetailPresenter:JobDeatailPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_deatail)
        activity = this
        init()
        listeners()
        jobid = intent.getStringExtra("jobid")
        Log.d("checkjobid",jobid.toString())
        jobsDetailPresenter = JobDeatailPresenter(activity as JobDeatailActivity,this)
        jobsDetailPresenter?.getJobsById(jobid)
        //sedata
//        tv_experiencetime?.text = data?.company?.experience


    }

    private fun init() {
        btn_applyjob = findViewById(R.id.btn_applyjob)
        img_message = findViewById(R.id.img_message)
        tv_experiencetime = findViewById(R.id.tv_experiencetime)
        tv_skills = findViewById(R.id.tv_skills)
        tv_category = findViewById(R.id.tv_category)
        tv_city = findViewById(R.id.tv_city)
        tv_country = findViewById(R.id.tv_country)
        tv_jobtype = findViewById(R.id.tv_jobtype)
        tv_pricedetail = findViewById(R.id.tv_pricedetail)
        tv_location = findViewById(R.id.tv_location)
        tv_phonenumber = findViewById(R.id.tv_phonenumber)
        tv_compadress = findViewById(R.id.tv_compadress)
        tv_email = findViewById(R.id.tv_email)
        tv_planofaction = findViewById(R.id.tv_planofaction)
        tv_jobdescription = findViewById(R.id.tv_jobdescription)
        linear_jobdescription = findViewById(R.id.linear_jobdescription)
        linear_description = findViewById(R.id.linear_description)
        tv_jobdetailsdescription = findViewById(R.id.tv_jobdetailsdescription)
    }

    private fun listeners() {
        btn_applyjob?.setOnClickListener(this)
        img_message?.setOnClickListener(this)
        tv_jobdescription?.setOnClickListener(this)
        tv_jobdetailsdescription?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == btn_applyjob) {
            var intent = Intent(activity, ApplyJobActivity::class.java)
            intent.putExtra("jobid",jobid)
            startActivity(intent)
        } else if (p0 == img_message) {
            var intent = Intent(activity, ChatDetailActivity::class.java)
            startActivity(intent)
        }else if (p0==tv_jobdetailsdescription){
            tv_jobdetailsdescription?.setTextColor(Color.parseColor("#EECB4F"))
            tv_jobdescription?.setTextColor(Color.parseColor("#FFFFFFFF"))
            linear_description?.visibility = View.VISIBLE
            linear_jobdescription?.visibility = View.GONE

        }else if (p0==tv_jobdescription){
            linear_description?.visibility = View.GONE
            linear_jobdescription?.visibility = View.VISIBLE
            tv_jobdescription?.setTextColor(Color.parseColor("#EECB4F"))
            tv_jobdetailsdescription?.setTextColor(Color.parseColor("#FFFFFFFF"))




        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity,msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity,fragmentManager)
    }

    override fun hideDialog() {
       Utils.hideDialog()
    }

    override fun setData(activity: JobDeatailActivity, data: GetJobByIdData?) {
        this.data = data
        tv_experiencetime?.text =data?.company?.experience +  "Years"
        tv_skills?.text =data?.company?.skills
        tv_category?.text = data?.category?.category
        tv_city?.text = "-" + data?.location
        tv_country?.text   =  data?.country
        tv_pricedetail?.text = "$"+data?.priceTo.toString()+ "/m"
        tv_jobtype?.text =data?.jobType
        tv_location?.text =data?.location + "," + data?.country
        tv_phonenumber?.text =  "+" + data?.company?.phoneNumber
        tv_compadress?.text =  data?.company?.address
        tv_email?.text =  data?.company?.emailId
        tv_planofaction?.text =   data?.planOfAction


    }


}