package com.app.humanresource.Fragments.FavouriteFragment.Presenter

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.FavouritesActivity.FavouritesActivity
import com.app.humanresource.Adapter.FavouritesRecyclerAdapter
import com.app.humanresource.Fragments.FavouriteFragment.View.FavouriteView
import com.app.humanresource.Handler.GetFavJobbyidsHandler
import com.app.humanresource.Handler.RemoveJobsFromWishlistHandler
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsExamples
import com.app.humanresource.Models.RemoveJobsFromWishlist.RemoveJobsFromFav
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices
import com.app.humanresource.interfaces.RemoveFromFav

class FavouritePresenter(
    private val activity: FavouritesActivity,
    private val favouriteView: FavouriteView,
    private val fav_recyclerview: RecyclerView?,
    private val removeFromFav: RemoveFromFav
) {
    var favouritesRecyclerAdapter: FavouritesRecyclerAdapter? = null
    fun getfavjobsByid() {
        if (Utils.isNetworkConnected(activity!!)) {
            var userid = CSPreferences.readString(activity, Utils.USERID)
            favouriteView.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.getfavjobsbyid(userid!!, object : GetFavJobbyidsHandler {
                    override fun onSuccess(getFavjobsExample: GetFavjobsExamples?) {
                        favouriteView.hideDialog()
                        if (getFavjobsExample?.isSuccess == true) {
                            favouritesRecyclerAdapter = FavouritesRecyclerAdapter(activity,getFavjobsExample.data,removeFromFav)
                            fav_recyclerview?.layoutManager = LinearLayoutManager(
                                activity,
                                LinearLayoutManager.VERTICAL, false
                            )
                            fav_recyclerview?.setHasFixedSize(true)



                            favouriteView.setData(activity,getFavjobsExample.data)
                            fav_recyclerview?.adapter = favouritesRecyclerAdapter
                        } else {
                            favouriteView.showMessage(activity, getFavjobsExample?.message)
                        }
                    }

                    override fun onError(message: String?) {
                        favouriteView.showMessage(activity, message)
                    }
                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }



    fun removejobsfromWishlist(jobid:String) {
        if (Utils.isNetworkConnected(activity!!)) {
//            favouriteView.showDialog(activity)
            WebServices.Factory1.getInstance()
                ?.removejobsfromWishlist(jobid!!, object : RemoveJobsFromWishlistHandler {
                    override fun onSuccess(removeJobsFromWishlist: RemoveJobsFromFav?) {
                        favouriteView.hideDialog()
                        if (removeJobsFromWishlist?.isSuccess==true){
                            favouriteView.showMessage(activity,removeJobsFromWishlist.message)
                        }else{
                            favouriteView.showMessage(activity,removeJobsFromWishlist?.message)

                        }
                    }

                    override fun onError(message: String?) {
                        favouriteView.showMessage(activity,message)
                    }

                })
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show()

        }

    }
}