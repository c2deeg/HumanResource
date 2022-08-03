package com.app.humanresource.Activities.FavouritesActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.app.humanresource.Adapter.FavouritesRecyclerAdapter
import com.app.humanresource.Fragments.FavouriteFragment.Presenter.FavouritePresenter
import com.app.humanresource.Fragments.FavouriteFragment.View.FavouriteView
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsDatum
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils
import com.app.humanresource.interfaces.RemoveFromFav

class FavouritesActivity : AppCompatActivity(), FavouriteView, RemoveFromFav, View.OnClickListener {
    private var listdata: List<GetFavjobsDatum> = ArrayList()
    var activity: Activity? = null
    var fav_recyclerview: RecyclerView? = null
    var favouritePresenter: FavouritePresenter? = null
    var animation_view: LottieAnimationView? = null
    var favouritesRecyclerAdapter: FavouritesRecyclerAdapter? = null
    var img_back: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        activity = this
        init()
        listeners()
        favouritePresenter =
            FavouritePresenter(activity as FavouritesActivity, this, fav_recyclerview,this)
        favouritePresenter?.getfavjobsByid()
    }


    private fun init() {
        fav_recyclerview = findViewById(R.id.fav_recyclerview)
        animation_view = findViewById(R.id.animation_view)
        img_back = findViewById(R.id.img_back)
    }

    private fun listeners() {
        img_back?.setOnClickListener(this)
    }
    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity, fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun setData(activity: FragmentActivity, data: List<GetFavjobsDatum>) {
        this.listdata = data
        if (listdata.isEmpty()) {
            animation_view?.visibility = View.VISIBLE
        } else {
            animation_view?.visibility = View.GONE
        }

    }

    override fun onItemClick2(
        data: MutableList<GetFavjobsDatum>,
        postion: Int,
        imgRemovefav: ImageView
    ) {

        favouritePresenter?.removejobsfromWishlist(data.get(postion).id)
        favouritesRecyclerAdapter?.notifyDataSetChanged()
        var intent = Intent(activity, FavouritesActivity::class.java)
        startActivity(intent)
        finish()


    }

    override fun onClick(p0: View?) {
        if (p0==img_back){
          finish()
        }
    }

}