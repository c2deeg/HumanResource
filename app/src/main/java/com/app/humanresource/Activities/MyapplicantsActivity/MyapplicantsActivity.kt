package com.app.humanresource.Activities.MyapplicantsActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyapplicantsActivity.Presenter.MyapplicantsPresenter
import com.app.humanresource.Activities.MyapplicantsActivity.View.MyapplicantsView
import com.app.humanresource.Adapter.ApplicantsRecyclerAdapter
import com.app.humanresource.R

class MyapplicantsActivity : AppCompatActivity(), View.OnClickListener,MyapplicantsView {
    var img_back:ImageView?=null
    var applicantsrecyclerview:RecyclerView?=null
    var applicantsRecyclerAdapter:ApplicantsRecyclerAdapter?=null
    var myapplicantsPresenter:MyapplicantsPresenter?=null
    var activity:Activity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myapplicants)
        activity = this
        init()
        listeners()
        var jobid = intent.getStringExtra("jobid")
        myapplicantsPresenter = MyapplicantsPresenter(activity as MyapplicantsActivity,this,applicantsrecyclerview,jobid)
       myapplicantsPresenter?.getjobapplicants()
    }
    private fun init(){
        applicantsrecyclerview = findViewById(R.id.applicantsrecyclerview)
        img_back = findViewById(R.id.img_back)

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

    }

    override fun showDialog(activity: Activity?) {
    }

    override fun hideDialog() {
    }
}