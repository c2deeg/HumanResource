package com.app.humanresource.Fragments.FavouriteFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Adapter.FavouritesRecyclerAdapter
import com.app.humanresource.Fragments.FavouriteFragment.View.FavouriteView
import com.app.humanresource.Handler.GetFavJobbyidsHandler
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsExamples
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class FavouritePresenter(
    private val activity: FragmentActivity,
    private val favouriteView: FavouriteView,
    private val fav_recyclerview: RecyclerView?
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
                            favouritesRecyclerAdapter = FavouritesRecyclerAdapter(activity,getFavjobsExample.data)
                            fav_recyclerview?.layoutManager = LinearLayoutManager(
                                activity,
                                LinearLayoutManager.VERTICAL, false
                            )
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
}