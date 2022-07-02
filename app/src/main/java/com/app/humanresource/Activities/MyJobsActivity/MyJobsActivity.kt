package com.app.humanresource.Activities.MyJobsActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyJobsActivity.Presenter.MyJobPresenter
import com.app.humanresource.Activities.MyJobsActivity.View.MyJobView
import com.app.humanresource.Adapter.MyJobsAdapter
import com.app.humanresource.R

class MyJobsActivity : AppCompatActivity(), View.OnClickListener, MyJobView {
    var myjobsrecyclerview: RecyclerView? = null
    var img_back: ImageView? = null
    var activity: Activity? = null
    var myJobPresenter: MyJobPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_jobs)
        activity = this
        init()
        listeners()
        myJobPresenter = MyJobPresenter(activity as MyJobsActivity,this,myjobsrecyclerview)
        myJobPresenter?.getemployerCreatedJobId()

    }

    private fun init() {

        myjobsrecyclerview = findViewById(R.id.myjobsrecyclerview)
        img_back = findViewById(R.id.img_back)

    }

    private fun listeners() {
        img_back?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0 == img_back) {
            finish()
        }
    }

    override fun showMessage(activity: Activity?, msg: String?) {
        com.app.humanresource.Utils.Utils.showMessage(activity, msg)
    }

    override fun showDialog(activity: Activity?) {
        com.app.humanresource.Utils.Utils.showDialogMethod(activity, activity?.fragmentManager)
    }

    override fun hideDialog() {
        com.app.humanresource.Utils.Utils.hideDialog()
    }

}