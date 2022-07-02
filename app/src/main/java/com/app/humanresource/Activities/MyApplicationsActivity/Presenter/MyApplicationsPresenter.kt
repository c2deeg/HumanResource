package com.app.humanresource.Activities.MyApplicationsActivity.Presenter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyApplicationsActivity.MyApplicationsActivity
import com.app.humanresource.Activities.MyApplicationsActivity.View.MyApplicationsView
import com.app.humanresource.Adapter.MyApplicationsAdapter
import com.app.humanresource.Handler.GetAppliedjobsbyUseridHandler
import com.app.humanresource.Models.getapplyjobsbyidmodels.GetApplyJobsByIdExamples
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class MyApplicationsPresenter(
    private val activity: MyApplicationsActivity,
    private val myApplicationsView: MyApplicationsView,
    private val myapplicationrecyclerview: RecyclerView?
) {
    var myApplicationsAdapter:MyApplicationsAdapter?=null

    fun getaplliedjobbyuserid(){
        var userid:String?=null
        myApplicationsView.showDialog(activity)
        userid = CSPreferences.readString(activity,Utils.USERID)
        WebServices.getInstance()?.getaplliedjobbyuserid(userid!!,object :GetAppliedjobsbyUseridHandler{
            override fun onSuccess(getapplyJobByidExample: GetApplyJobsByIdExamples?) {
                if (getapplyJobByidExample?.isSuccess==true){
                    myApplicationsView.hideDialog()
                    myApplicationsView.showMessage(activity,getapplyJobByidExample.message)
                    myApplicationsAdapter = MyApplicationsAdapter(activity as MyApplicationsActivity,
                        getapplyJobByidExample.data)
                    myapplicationrecyclerview?.layoutManager = LinearLayoutManager(activity,
                        LinearLayoutManager.VERTICAL,false)
                    myapplicationrecyclerview?.adapter = myApplicationsAdapter


                }
            }

            override fun onError(message: String?) {
               myApplicationsView.hideDialog()
                myApplicationsView.showMessage(activity,message)
            }

        })
    }
}