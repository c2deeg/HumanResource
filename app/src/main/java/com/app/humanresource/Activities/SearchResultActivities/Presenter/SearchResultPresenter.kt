package com.app.humanresource.Activities.SearchResultActivities.Presenter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.app.humanresource.Activities.SearchResultActivities.SearchResultActivity
import com.app.humanresource.Activities.SearchResultActivities.View.SearchResultView
import com.app.humanresource.Adapter.FilterRecyclerAdapter
import com.app.humanresource.Adapter.SearchResultRecyclerviewAdapter
import com.app.humanresource.Handler.AddtoFavHandler
import com.app.humanresource.Handler.ApplyFilterHandler
import com.app.humanresource.Handler.SearchHandler
import com.app.humanresource.Models.AddtoFavouriteModels.AddToFavExample
import com.app.humanresource.Models.ApplyfilterModels.JobFilterExamples
import com.app.humanresource.Models.SearchModels.SearchExamples
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices
import com.app.humanresource.interfaces.AddtoFavInterface
import com.app.humanresource.interfaces.Popularjobsclickinterfaces

class SearchResultPresenter(
    private val activity: SearchResultActivity,
    private val searchResultView: SearchResultView,
    private val searchresult_recyclerview: RecyclerView?,
    private val filterresult_recyclerview: RecyclerView?,
    private val animation_view: LottieAnimationView?,
    private val popularjobsclickinterfaces: AddtoFavInterface
) {
    var searchResultRecyclerviewAdapter: SearchResultRecyclerviewAdapter? = null
    fun searchjobs(searchtitle: String) {
        searchResultView.showDialog(activity)
        if (Utils.isNetworkConnected(activity!!)) {
            var jobFilterExamples: JobFilterExamples? = null
            WebServices.getInstance()
                ?.searchPI(searchtitle, object : SearchHandler {
                    override fun onSuccess(searchExamples: SearchExamples?) {
                        if (searchExamples?.isSuccess == true) {
                            searchResultView.hideDialog()
                            if (searchExamples.message.size==0){
                                activity.animation_view?.visibility = View.VISIBLE
                            }else{
                                activity.animation_view?.visibility = View.GONE

                            }

                            searchResultRecyclerviewAdapter =
                                SearchResultRecyclerviewAdapter(
                                    activity as SearchResultActivity,
                                    searchExamples.message,
                              popularjobsclickinterfaces  )
                            searchresult_recyclerview?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                            searchresult_recyclerview?.adapter = searchResultRecyclerviewAdapter

                        }
                    }
                    override fun onError(message: String?) {
                        searchResultView?.hideDialog()
                        searchResultView?.showMessage(activity, message)
                    }


                })

        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    fun applyfilters(
        categoryid: String,
        jobtype: String,
        location: String,
        pricefrom: String,
        priceTo: String
    ) {

        var filterrecycleradapter: FilterRecyclerAdapter? = null
        if (Utils.isNetworkConnected(activity!!)) {
            WebServices.getInstance()
                ?.applyfilters(categoryid!!, "fulltime", location!!, "111", "1111", object :
                    ApplyFilterHandler {
                    override fun onSuccess(jobFilterExamples: JobFilterExamples?) {
                        if (jobFilterExamples?.isSuccess == true) {
                            filterrecycleradapter = FilterRecyclerAdapter(
                                activity as SearchResultActivity,
                                jobFilterExamples.data
                            )
                            if (jobFilterExamples.data.size==0){
                                activity.animation_view?.visibility = View.VISIBLE
                            }else{
                                activity.animation_view?.visibility = View.GONE

                            }
                            filterresult_recyclerview?.layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//                            searchResultView?.showMessage(activity, jobFilterExamples.message)
                            searchResultView?.getfilterdata(activity, jobFilterExamples.data)

                        } else {
                            searchResultView?.showMessage(activity, jobFilterExamples?.message)
                        }
                    }

                    override fun onError(message: String?) {
                        searchResultView?.hideDialog()
                        searchResultView?.showMessage(activity, message)
                    }

                })

        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    fun addtofavMethod(id: String) {
        var userid: String? = null
        userid = CSPreferences.readString(activity!!, Utils.USERID)
        if (Utils.isNetworkConnected(activity!!)) {
            WebServices.Factory1.getInstance()?.addtoFavMethod(userid, id, "true", object :
                AddtoFavHandler {
                override fun onSuccess(addToFavExample: AddToFavExample?) {
                    if (addToFavExample != null) {
                        if (addToFavExample?.isSuccess == true) {
                            searchResultView?.showMessage(activity, addToFavExample.message)
                        } else {
                            searchResultView?.showMessage(activity, addToFavExample.message)
                        }
                    }
                }
                override fun onError(message: String?) {
                    searchResultView?.hideDialog()
                    searchResultView?.showMessage(activity, message)
                }
            })

        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }



}