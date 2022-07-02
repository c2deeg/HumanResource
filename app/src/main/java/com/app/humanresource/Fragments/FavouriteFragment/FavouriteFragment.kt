package com.app.humanresource.Fragments.FavouriteFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.app.humanresource.Adapter.FavouritesRecyclerAdapter
import com.app.humanresource.Fragments.FavouriteFragment.Presenter.FavouritePresenter
import com.app.humanresource.Fragments.FavouriteFragment.View.FavouriteView
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsDatum
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils

class FavouriteFragment : Fragment(), FavouriteView {
    private lateinit var listdata: List<GetFavjobsDatum>
    var activity: Activity? = null
    var fav_recyclerview: RecyclerView? = null
    var favouritePresenter: FavouritePresenter? = null
    var animation_view: LottieAnimationView? = null
    var favouritesRecyclerAdapter: FavouritesRecyclerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        activity = getActivity()
        init(view)
        listeners(view)
        favouritePresenter =
            FavouritePresenter(activity as FragmentActivity, this, fav_recyclerview)
        favouritePresenter?.getfavjobsByid()
        listdata = ArrayList<GetFavjobsDatum>()
        if (listdata.isEmpty()){
            animation_view?.visibility = View.VISIBLE
        }else{
            animation_view?.visibility = View.GONE
        }


        return view
    }


    private fun init(view: View?) {
        fav_recyclerview = view?.findViewById(R.id.fav_recyclerview)
        animation_view = view?.findViewById(R.id.animation_view)
    }

    private fun listeners(view: View?) {
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, requireActivity()!!.fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun setData(activity: FragmentActivity, data: List<GetFavjobsDatum>) {
        this.listdata = data
    }


}