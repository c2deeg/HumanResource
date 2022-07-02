package com.app.humanresource.Activities.MyJobsActivity.Presenter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyJobsActivity.MyJobsActivity
import com.app.humanresource.Activities.MyJobsActivity.View.MyJobView
import com.app.humanresource.Adapter.MyJobsAdapter
import com.app.humanresource.Handler.GetEmployerCreatedJobHandler
import com.app.humanresource.Models.GetEmployerCreatedJob.GetEmployerCreatedJob
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class MyJobPresenter(
    private val activity: MyJobsActivity,
    private val myJobView: MyJobView,
    private val myjobsrecyclerview: RecyclerView?
) {

    fun getemployerCreatedJobId(){
        var userid:String?=null
        myJobView.showDialog(activity)
        userid = CSPreferences.readString(activity, Utils.USERID)
        WebServices.getInstance()?.getemployercreatedjob(userid!!,object :
            GetEmployerCreatedJobHandler {
            override fun onSuccess(getEmployerCreatedJob: GetEmployerCreatedJob?) {
                if (getEmployerCreatedJob?.isSuccess==true){
                    myJobView.hideDialog()
                    var myJobsAdapter = MyJobsAdapter(activity ,getEmployerCreatedJob.data)
                    myjobsrecyclerview?.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    myjobsrecyclerview?.adapter = myJobsAdapter
                }else{

                }
            }

            override fun onError(message: String?) {
                myJobView.showMessage(activity,message)
            }


        })
    }

}