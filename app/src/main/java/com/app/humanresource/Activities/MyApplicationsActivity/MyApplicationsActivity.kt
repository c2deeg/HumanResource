package com.app.humanresource.Activities.MyApplicationsActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyApplicationsActivity.Presenter.MyApplicationsPresenter
import com.app.humanresource.Activities.MyApplicationsActivity.View.MyApplicationsView
import com.app.humanresource.Adapter.MyApplicationsAdapter
import com.app.humanresource.R

class MyApplicationsActivity : AppCompatActivity(), View.OnClickListener,MyApplicationsView {
    var myapplicationrecyclerview:RecyclerView?=null
    var myApplicationsAdapter:MyApplicationsAdapter?=null
    var img_back:ImageView?=null
    var activity:Activity?=null
    var myApplicationsPresenter:MyApplicationsPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_applications)
        activity = this
        inits()
        listners()
        myApplicationsPresenter  = MyApplicationsPresenter(activity as MyApplicationsActivity,this,myapplicationrecyclerview)

        myApplicationsPresenter?.getaplliedjobbyuserid()

    }
    private fun inits(){
        myapplicationrecyclerview = findViewById(R.id.myapplicationrecyclerview)
        img_back = findViewById(R.id.img_back)

    }
    private fun listners(){
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
        com.app.humanresource.Utils.Utils.showDialogMethod(activity,fragmentManager)
    }

    override fun hideDialog() {
        com.app.humanresource.Utils.Utils.hideDialog()
    }
}