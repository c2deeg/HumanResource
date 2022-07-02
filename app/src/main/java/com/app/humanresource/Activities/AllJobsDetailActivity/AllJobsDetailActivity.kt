package com.app.humanresource.Activities.AllJobsDetailActivity

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.AllJobsDetailActivity.Presenter.AllJobsDetailPresenter
import com.app.humanresource.Activities.AllJobsDetailActivity.VIew.AllJobsDetailView
import com.app.humanresource.Adapter.MostRecentRecyclerAdapter
import com.app.humanresource.Adapter.MostRelevantRecyclerAdapter
import com.app.humanresource.Models.GetJobsModels.GetJobsDatum
import com.app.humanresource.Models.RecentPostModels.RecentPostsMessage
import com.app.humanresource.R
import com.app.humanresource.Utils.Utils
import com.app.humanresource.interfaces.Popularjobsclickinterfaces

class AllJobsDetailActivity : AppCompatActivity(), View.OnClickListener,AllJobsDetailView,Popularjobsclickinterfaces {
    private  var recentData: List<RecentPostsMessage> = ArrayList<RecentPostsMessage>()
    private  var relvantdata: List<GetJobsDatum> = ArrayList<GetJobsDatum>()
    var activity: Activity? = null
    var most_relevantjobsRecyclerview: RecyclerView? = null
    var mostrecentjobs_recyclerview: RecyclerView? = null
    var mostRelevantRecyclerAdapter: MostRelevantRecyclerAdapter? = null
    var mostRecentRecyclerAdapter: MostRecentRecyclerAdapter? = null
    var tv_mostrelevantjobs: TextView? = null
    var tv_mostrecentjobs: TextView? = null
    var allJobsDetailPresenter:AllJobsDetailPresenter?=null
    var check:String?=null
    var flag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_jobs_detail)
        activity = this
        init()
        listeners()
        check = intent.getStringExtra("role").toString()
        allJobsDetailPresenter = AllJobsDetailPresenter(activity as AllJobsDetailActivity,this,most_relevantjobsRecyclerview,mostrecentjobs_recyclerview)
        allJobsDetailPresenter!!.getrecentpostJobs()
        allJobsDetailPresenter!!.getallJobs()
        if (check.equals("recent")){
            tv_mostrecentjobs?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            tv_mostrelevantjobs?.setBackgroundDrawable(resources.getDrawable(R.drawable.roundcorner2))
            tv_mostrecentjobs?.setTextColor(Color.BLACK)
            tv_mostrelevantjobs?.setTextColor(Color.WHITE)
            most_relevantjobsRecyclerview?.visibility =View.GONE
            mostrecentjobs_recyclerview?.visibility = View.VISIBLE
        }
    }

    private fun init() {
        most_relevantjobsRecyclerview = findViewById(R.id.most_relevantjobs)
        mostrecentjobs_recyclerview = findViewById(R.id.mostrecentjobs_recyclerview)
        tv_mostrelevantjobs = findViewById(R.id.tv_mostrelevantjobs)
        tv_mostrecentjobs = findViewById(R.id.tv_mostrecentjobs)

    }

    private fun listeners() {
        tv_mostrelevantjobs?.setOnClickListener(this)
        tv_mostrecentjobs?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0==tv_mostrelevantjobs){
            tv_mostrecentjobs?.setBackgroundDrawable(resources.getDrawable(R.drawable.roundcorner2))
            tv_mostrelevantjobs?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            tv_mostrecentjobs?.setTextColor(Color.WHITE)
            tv_mostrelevantjobs?.setTextColor(Color.BLACK)
            mostrecentjobs_recyclerview?.visibility =View.GONE
            most_relevantjobsRecyclerview?.visibility = View.VISIBLE
        }else if(p0==tv_mostrecentjobs){
            tv_mostrecentjobs?.setBackgroundDrawable(resources.getDrawable(R.drawable.appgoldenbackground))
            tv_mostrelevantjobs?.setBackgroundDrawable(resources.getDrawable(R.drawable.roundcorner2))
            tv_mostrecentjobs?.setTextColor(Color.BLACK)
            tv_mostrelevantjobs?.setTextColor(Color.WHITE)
            most_relevantjobsRecyclerview?.visibility =View.GONE
            mostrecentjobs_recyclerview?.visibility = View.VISIBLE

        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        Utils.showMessage(activity,msg)
    }

    override fun showDialog(activity: Activity?) {
       Utils.showDialogMethod(activity,fragmentManager)
    }

    override fun hideDialog() {
     Utils.hideDialog()
    }

    override fun setrelevantData(activity: AllJobsDetailActivity, data: List<GetJobsDatum>) {
        this.relvantdata = data
        most_relevantjobsRecyclerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mostRelevantRecyclerAdapter = MostRelevantRecyclerAdapter(
            activity as FragmentActivity, data as MutableList<GetJobsDatum>, this)
        most_relevantjobsRecyclerview?.adapter = mostRelevantRecyclerAdapter
    }

    override fun setrecentData(activity: AllJobsDetailActivity, message: List<RecentPostsMessage>) {
        this.recentData = message
        mostrecentjobs_recyclerview?.layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                            mostRecentRecyclerAdapter = MostRecentRecyclerAdapter(
                                activity as FragmentActivity,
                                recentData as MutableList<RecentPostsMessage>,this
                            )
                            mostrecentjobs_recyclerview?.adapter = mostRecentRecyclerAdapter
    }

    override fun onItemClick(postion: Int, imgFav: ImageView) {
        if (flag) {
            imgFav!!.setColorFilter(Color.RED)
            allJobsDetailPresenter?.addtofavMethod(relvantdata.get(postion).id)
            allJobsDetailPresenter?.addtofavMethod(recentData.get(postion).id)
        } else {
            imgFav!!.setColorFilter(Color.parseColor("#DADADA"))
        }
        flag = !flag
    }
}