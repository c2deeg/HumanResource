package com.app.humanresource.Activities.SearchResultActivities

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.app.humanresource.Activities.SearchResultActivities.Presenter.SearchResultPresenter
import com.app.humanresource.Activities.SearchResultActivities.View.SearchResultView
import com.app.humanresource.Models.ApplyfilterModels.JobFilterDatum
import com.app.humanresource.Models.RecentPostModels.RecentPostsMessage
import com.app.humanresource.Models.SearchModels.SearchMessage
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils
import com.app.humanresource.interfaces.AddtoFavInterface
import com.app.humanresource.interfaces.Popularjobsclickinterfaces

class SearchResultActivity : AppCompatActivity(), SearchResultView, AddtoFavInterface {
    private lateinit var searchdata: List<SearchMessage>
    private lateinit var filterdata: List<JobFilterDatum>
    var activity: Activity? = null
    var searchresult_recyclerview: RecyclerView? = null
    var filterresult_recyclerview: RecyclerView? = null
    var animation_view: LottieAnimationView? = null
    var searchResultPresenter: SearchResultPresenter? = null
    var categoryid:String?=null
    var search:String?=null
    var locationtext:String?=null
    var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        activity = this
        search = intent.getStringExtra("search")
        var filter = intent.getStringExtra("filter")

        categoryid  = intent.getStringExtra("categoryid")
        locationtext  = intent.getStringExtra("locationtext")

        init()
        listeners()
        filterdata = ArrayList<JobFilterDatum>()
        searchdata = ArrayList<SearchMessage>()

        searchResultPresenter =
            SearchResultPresenter(activity as SearchResultActivity, this, searchresult_recyclerview,filterresult_recyclerview,animation_view,this)
        if (search!=null){
            searchResultPresenter!!.searchjobs(search!!)
            filterresult_recyclerview?.visibility = View.GONE
            searchresult_recyclerview?.visibility = View.VISIBLE
        }else{
            searchResultPresenter!!.applyfilters(categoryid!!,"fulltime",locationtext!!,"1000","1000");
            filterresult_recyclerview?.visibility = View.VISIBLE
            searchresult_recyclerview?.visibility = View.GONE
        }

    }

    private fun init() {
        searchresult_recyclerview = findViewById(R.id.searchresult_recyclerview)
        filterresult_recyclerview = findViewById(R.id.filterresult_recyclerview)
        animation_view = findViewById(R.id.animation_view)
    }

    private fun listeners() {

    }

    override fun showMessage(activity: Activity?, msg: String?) {
//        Utils.showMessage(activity,msg)

    }

    override fun showDialog(activity: Activity?) {
        Utils.showDialogMethod(activity,fragmentManager)
    }

    override fun hideDialog() {
        Utils.hideDialog()
    }

    override fun getfilterdata(activity: SearchResultActivity, data: List<JobFilterDatum>) {
        this.filterdata = data
    }

    override fun getsearchdata(activity: SearchResultActivity, message: List<SearchMessage>) {
      this.searchdata = message

    }

    override fun onItemClick(position: Int, imgFav: ImageView, data: MutableList<SearchMessage>) {
        if (flag) {
            imgFav!!.setColorFilter(Color.RED)
            searchResultPresenter?.addtofavMethod(searchdata.get(position).id)
         } else {
            imgFav!!.setColorFilter(Color.parseColor("#DADADA"))
        }
        flag = !flag
    }

}