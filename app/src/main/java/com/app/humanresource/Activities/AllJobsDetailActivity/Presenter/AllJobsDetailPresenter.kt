package com.app.humanresource.Activities.AllJobsDetailActivity.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.AllJobsDetailActivity.AllJobsDetailActivity
import com.app.humanresource.Activities.AllJobsDetailActivity.VIew.AllJobsDetailView
import com.app.humanresource.Adapter.MostRecentRecyclerAdapter
import com.app.humanresource.Adapter.MostRelevantRecyclerAdapter
import com.app.humanresource.Handler.AddtoFavHandler
import com.app.humanresource.Handler.GetalljobsHandler
import com.app.humanresource.Handler.RecentPostHandler
import com.app.humanresource.Models.AddtoFavouriteModels.AddToFavExample
import com.app.humanresource.Models.GetJobsModels.GetJobsExample
import com.app.humanresource.Models.RecentPostModels.RecentPostsExample
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class AllJobsDetailPresenter(
    private val activity: AllJobsDetailActivity,
    private val allJobsDetailView: AllJobsDetailView,
    private val most_relevantjobsRecyclerview: RecyclerView?,
    private val mostrecentjobs_recyclerview: RecyclerView?

) {
    var mostRelevantRecyclerAdapter: MostRelevantRecyclerAdapter? = null
    var mostRecentRecyclerAdapter: MostRecentRecyclerAdapter? = null

    fun getallJobs() {
        if (Utils.isNetworkConnected(activity!!)) {
            allJobsDetailView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getalljobsAPI(object :
                GetalljobsHandler {
                override fun onSuccess(getJobsExample: GetJobsExample?) {
                    allJobsDetailView?.hideDialog()
                    if (getJobsExample != null) {
                        if (getJobsExample.getIsSuccess() === true) {
                            allJobsDetailView.setrelevantData(activity, getJobsExample.data)

                        } else {
                            allJobsDetailView?.showMessage(activity, getJobsExample.getMessage())
                        }
                    } else {
                        allJobsDetailView?.showMessage(activity, getJobsExample?.getMessage())
                    }
                }

                override fun onError(message: String?) {
                    allJobsDetailView?.hideDialog()
                    allJobsDetailView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    fun getrecentpostJobs() {
        if (Utils.isNetworkConnected(activity!!)) {
//            homeView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getrecentpostMethod(object :
                RecentPostHandler {
                override fun onSuccess(recentPostsExample: RecentPostsExample?) {
                    allJobsDetailView?.hideDialog()
                    if (recentPostsExample != null) {
                        if (recentPostsExample.getIsSuccess() === true) {
                            allJobsDetailView.setrecentData(activity,recentPostsExample.message)


//
                        }
                    }
                }

                override fun onError(message: String?) {
                    allJobsDetailView?.hideDialog()
                    allJobsDetailView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    //addtofavMethod
    fun addtofavMethod(id: String) {
        var userid: String? = null
        userid = CSPreferences.readString(activity!!, Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            WebServices.Factory1.getInstance()?.addtoFavMethod(userid, id, "true", object :
                AddtoFavHandler {
                override fun onSuccess(addToFavExample: AddToFavExample?) {
                    if (addToFavExample != null) {
                        if (addToFavExample?.isSuccess == true) {
                            allJobsDetailView?.showMessage(activity, addToFavExample.message)
                        } else {
                            allJobsDetailView?.showMessage(activity, addToFavExample.message)
                        }
                    }
                }
                override fun onError(message: String?) {
                    allJobsDetailView?.hideDialog()
                    allJobsDetailView?.showMessage(activity, message)
                }
            })

        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
}