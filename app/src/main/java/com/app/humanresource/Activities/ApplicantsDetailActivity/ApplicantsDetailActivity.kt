package com.app.humanresource.Activities.ApplicantsDetailActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.app.humanresource.Activities.ApplicantsDetailActivity.Presenter.ApplicantsDetailPresenter
import com.app.humanresource.Activities.ApplicantsDetailActivity.View.ApplicantsDetailView
import com.app.humanresource.R

class ApplicantsDetailActivity : AppCompatActivity(), View.OnClickListener,ApplicantsDetailView {
    var img_back:ImageView?=null
    var activity:Activity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicants_detail)
        inits()
        listeners()
        var applicantsDetailPresenter = ApplicantsDetailPresenter(activity as ApplicantsDetailActivity,this)
    }
    private fun inits(){
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
        TODO("Not yet implemented")
    }

    override fun showDialog(activity: Activity?) {
        TODO("Not yet implemented")
    }

    override fun hideDialog() {
        TODO("Not yet implemented")
    }
}