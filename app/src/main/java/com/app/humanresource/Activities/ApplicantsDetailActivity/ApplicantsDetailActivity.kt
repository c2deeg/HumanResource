package com.app.humanresource.Activities.ApplicantsDetailActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.humanresource.Activities.ApplicantsDetailActivity.Presenter.ApplicantsDetailPresenter
import com.app.humanresource.Activities.ApplicantsDetailActivity.View.ApplicantsDetailView
import com.app.humanresource.Adapter.ApplicantsRecyclerAdapter
import com.app.humanresource.Models.GetApplieduserDetail.GetAppliedUserDetailData
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicantsApplyBy
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils

class ApplicantsDetailActivity : AppCompatActivity(), View.OnClickListener,ApplicantsDetailView {
    var img_back:ImageView?=null
    var activity:Activity?=null
    var tv_name:TextView?=null
    var tv_mail:TextView?=null
    var tv_city:TextView?=null
    var tv_salaryamount:TextView?=null
    var tv_jobtype:TextView?=null
    var tv_postion:TextView?=null
    var tv_description:TextView?=null
    var tv_firstcharacter:TextView?=null
    var tv_lastcharcter:TextView?=null
    var applicantsDetailPresenter:ApplicantsDetailPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicants_detail)
        activity = this
        inits()
        listeners()
        var jobid = intent.getStringExtra("jobid")
        var userid = intent.getStringExtra("userid")
       applicantsDetailPresenter = ApplicantsDetailPresenter(activity as ApplicantsDetailActivity,this,jobid,userid)
        applicantsDetailPresenter?.getjobapplicants()
    }
    private fun inits(){
        img_back = findViewById(R.id.img_back)
        tv_name = findViewById(R.id.tv_name)
        tv_mail = findViewById(R.id.tv_mail)
        tv_city = findViewById(R.id.tv_city)
        tv_salaryamount = findViewById(R.id.tv_salaryamount)
        tv_jobtype = findViewById(R.id.tv_jobtype)
        tv_postion = findViewById(R.id.tv_postion)
        tv_description = findViewById(R.id.tv_description)
        tv_firstcharacter = findViewById(R.id.tv_firstcharacter)
        tv_lastcharcter = findViewById(R.id.tv_lastcharcter)

    }
    private fun listeners(){
        img_back?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0==img_back){
            finish()
        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
      com.app.humanresource.Utils.Utils.showMessage(activity,msg)
    }

    override fun showDialog(activity: Activity?) {
        com.app.humanresource.Utils.Utils.showDialogMethod(activity,activity?.fragmentManager)
    }

    override fun hideDialog() {
       com.app.humanresource.Utils.Utils.hideDialog()
    }

    override fun setData(activity: ApplicantsDetailActivity, data: GetAppliedUserDetailData) {
        tv_name?.text = data.firstName + "" + data.lastName
        tv_mail?.text = data.email
        tv_description?.text  = data.describe
        tv_city?.text = data.country
        tv_firstcharacter?.text = data.firstName[0].toString()
        tv_lastcharcter?.text = data.lastName[0].toString()

    }

//    override fun setData(activity: ApplicantsDetailActivity, data: GetJobApplicantsApplyBy?) {
//        tv_name?.text = data?.userName
//    }
}