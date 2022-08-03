package com.app.humanresource.Fragments.HomeFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.SearchResultActivities.SearchResultActivity
import com.app.humanresource.Adapter.FilterRecyclerAdapter
import com.app.humanresource.Adapter.PopularJobsRecyclerAdapter
import com.app.humanresource.Adapter.RecentPostsRecyclerAdapter
import com.app.humanresource.Adapter.SearchResultRecyclerviewAdapter
import com.app.humanresource.Fragments.HomeFragment.View.HomeView
import com.app.humanresource.Handler.*
import com.app.humanresource.Models.AddtoFavouriteModels.AddToFavExample
import com.app.humanresource.Models.ApplyfilterModels.JobFilterExamples
import com.app.humanresource.Models.GetAllLocationModels.GetallLocationExample
import com.app.humanresource.Models.GetPopularjobsModels.PopularJobsExample
import com.app.humanresource.Models.GetallCategoryExample.GetallCategoryExample
import com.app.humanresource.Models.Profile.ProfileExample
import com.app.humanresource.Models.RecentPostModels.RecentPostsExample
import com.app.humanresource.Models.SearchModels.SearchExamples
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class HomePresenter(
    private val activity: FragmentActivity?,
    private val homeView: HomeView?,
    private val popularjobs_recyclerview: RecyclerView?,
    private val recentpost_recyclerview: RecyclerView?
) {
    var popularJobsRecyclerAdapter: PopularJobsRecyclerAdapter? = null
    var recentPostsRecyclerAdapter: RecentPostsRecyclerAdapter? = null


//    fun getallJobs() {
//        if (Utils.isNetworkConnected(activity!!)) {
//            homeView?.showDialog(activity)
//            WebServices.Factory1.getInstance()?.getalljobsAPI(object :
//                GetalljobsHandler {
//                override fun onSuccess(getJobsExample: GetJobsExample?) {
//                    homeView?.hideDialog()
//                    if (getJobsExample != null) {
//                        if (getJobsExample.getIsSuccess() === true) {
//                            homeView?.showMessage(activity, getJobsExample.message)
//                            popularjobs_recyclerview?.layoutManager =
//                                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//                            popularJobsRecyclerAdapter = PopularJobsRecyclerAdapter(
//                                activity as FragmentActivity, getJobsExample.data
//                            )
//                            popularjobs_recyclerview?.adapter = popularJobsRecyclerAdapter
//                        } else {
//                            homeView?.showMessage(activity, getJobsExample.getMessage())
//                        }
//                    } else {
//                        homeView?.showMessage(activity, getJobsExample?.getMessage())
//                    }
//                }
//                override fun onError(message: String?) {
//                    homeView?.hideDialog()
//                    homeView?.showMessage(activity, message)
//                }
//            })
//        } else {
//            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
//
//        }
//
//    }


    fun getrecentpostJobs() {
        if (Utils.isNetworkConnected(activity!!)) {
//            homeView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getrecentpostMethod(object :
                RecentPostHandler {
                override fun onSuccess(recentPostsExample: RecentPostsExample?) {
                    homeView?.hideDialog()
                    if (recentPostsExample != null) {
                        if (recentPostsExample.getIsSuccess() === true) {
                            homeView?.setlistData(activity, recentPostsExample.message)


//                            recentpost_recyclerview?.layoutManager =
//                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//                            recentPostsRecyclerAdapter = RecentPostsRecyclerAdapter(
//                                activity as FragmentActivity,
//                                recentPostsExample.message
//                            )
//                            recentpost_recyclerview?.adapter = recentPostsRecyclerAdapter
                        }

                    }

                }

                override fun onError(message: String?) {
                    homeView?.hideDialog()
                    homeView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }

    //getpopularjobs
    fun getpopularJobs() {
        if (Utils.isNetworkConnected(activity!!)) {
            homeView?.showDialog(activity)
            WebServices.Factory1.getInstance()?.getpopularjobsMethod(object :
                PopularjobsHandler {
                override fun onSuccess(popularJobsExample: PopularJobsExample?) {
                    homeView?.hideDialog()
                    if (popularJobsExample != null) {
                        if (popularJobsExample.getIsSuccess() === true) {

                            homeView?.setPopularjobsData(activity, popularJobsExample.message)

                        } else {
                        }
                    } else {

                    }
                }

                override fun onError(message: String?) {
                    homeView?.hideDialog()
                    homeView?.showMessage(activity, message)
                }
            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }
    }

    fun getallCategory() {
        if (Utils.isNetworkConnected(activity!!)) {
            WebServices.Factory1.getInstance()?.getallcategories(object :
                GetallCategoryHandler {
                override fun onSuccess(getallCategoryExample: GetallCategoryExample?) {

                    if (getallCategoryExample?.isSuccess == true) {
//                        homeView?.showMessage(activity,getallCategoryExample.message)
                        homeView?.setData(activity, getallCategoryExample.data)
                    } else {
                        homeView?.showMessage(activity, getallCategoryExample?.message)

                    }
                }

                override fun onError(message: String?) {
                    homeView?.hideDialog()
                    homeView?.showMessage(activity, message)

                }

            })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }
    }

    //getalllocations
    fun getallLocations() {
        if (Utils.isNetworkConnected(activity!!)) {
            WebServices.Factory1.getInstance()?.getallocations(object :
                GetallLocationHandler {
                override fun onSuccess(getallLocationExample: GetallLocationExample?) {

                    if (getallLocationExample?.isSuccess == true) {
//                        homeView?.showMessage(activity,getallLocationExample.message)
                        homeView?.setLocationData(activity, getallLocationExample.data)
                    } else {
                        homeView?.showMessage(activity, getallLocationExample?.message)
                    }
                }

                override fun onError(message: String?) {
                    homeView?.hideDialog()
                    homeView?.showMessage(activity, message)
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
            WebServices.Factory1.getInstance()
                ?.addtoFavMethod(userid, id, "true", object : AddtoFavHandler {
                    override fun onSuccess(addToFavExample: AddToFavExample?) {
                        if (addToFavExample != null) {
                            if (addToFavExample?.isSuccess == true) {
                                homeView?.showMessage(activity, addToFavExample.message)

                            } else {
                                homeView?.showMessage(activity, addToFavExample.message)
                            }
                        }
                    }

                    override fun onError(message: String?) {
                        homeView?.hideDialog()
                        homeView?.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }
    }

    fun getCurrentUser() {
        var id: String? = null
        var token: String? = null
        token = CSPreferences.readString(activity!!, Utils.TOKEN)
        id = CSPreferences.readString(activity!!, Utils.USERID)
        if (Utils.isNetworkConnected(activity)) {
            WebServices.Factory1.getInstance()
                ?.getCurrentUser(token!!, id!!, object : GetCurrentUserHandler {
                    override fun onSuccess(getUserExample: ProfileExample?) {
                        homeView?.hideDialog()
                        if (getUserExample?.isSuccess == true) {
                            homeView?.getData(activity, getUserExample.data)
                            CSPreferences.putString(
                                activity,
                                Utils.USERNAME,
                                getUserExample.data.userName
                            )
                            CSPreferences.putString(
                                activity,
                                Utils.CONTACTNUMBER,
                                getUserExample.data.phoneNumber
                            )
                            CSPreferences.putString(
                                activity,
                                Utils.USEREMAIL,
                                getUserExample.data.email
                            )


                        }
                    }

                    override fun onError(message: String?) {
                        homeView?.hideDialog()
                        homeView?.showMessage(activity, message)
                    }

                })

        } else {
            homeView?.hideDialog()

            Toast.makeText(activity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

//    fun applyfilters(categoryid:String,jobtype:String,location:String,priceform:String,priceTo:String) {
//        var FilterRecyclerAdapter:FilterRecyclerAdapter?=null
//        if (Utils.isNetworkConnected(activity!!)) {
//            WebServices.getInstance()
//                ?.applyfilters(categoryid, jobtype.trim(), location, "111", "1111", object : ApplyFilterHandler {
//                    override fun onSuccess(jobFilterExamples: JobFilterExamples?) {
//                        if (jobFilterExamples?.isSuccess == true) {
//
//
//                            homeView?.showMessage(activity, jobFilterExamples.message)
//
//                        } else {
//                            homeView?.showMessage(activity, jobFilterExamples?.message)
//                        }
//                    }
//
//                    override fun onError(message: String?) {
//                        homeView?.hideDialog()
//                        homeView?.showMessage(activity, message)
//                    }
//
//                })
//
//        } else {
//            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
//        }
//    }

    fun searchjobs(searchtitle: String) {
        if (Utils.isNetworkConnected(activity!!)) {
            WebServices.getInstance()
                ?.searchPI(searchtitle, object : SearchHandler {
                    override fun onSuccess(searchExamples: SearchExamples?) {
                        if (searchExamples?.isSuccess == true) {

                        }
                    }

                    override fun onError(message: String?) {
                        homeView?.hideDialog()
                        homeView?.showMessage(activity, message)
                    }


                })

        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }


}