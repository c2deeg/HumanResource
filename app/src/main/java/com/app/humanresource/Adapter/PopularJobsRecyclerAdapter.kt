package com.app.humanresource.Adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.JobDeatailActivity.JobDeatailActivity
import com.app.humanresource.Fragments.HomeFragment.Presenter.HomePresenter
import com.app.humanresource.Models.GetPopularjobsModels.PopularJobsMessage
import com.app.humanresource.R
import com.app.humanresource.interfaces.Popularjobsclickinterfaces
import de.hdodenhof.circleimageview.CircleImageView

class PopularJobsRecyclerAdapter(
    private val activity: FragmentActivity,
    private val arraydata: MutableList<PopularJobsMessage>,
    private val clickinterface: Popularjobsclickinterfaces

) :
    RecyclerView.Adapter<PopularJobsRecyclerAdapter.ViewHolder>() {


    var flag = true
    var homePresenter:HomePresenter?=null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularJobsRecyclerAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.popularjobsitem, parent, false)
        return PopularJobsRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularJobsRecyclerAdapter.ViewHolder, position: Int) {

        holder.tv_worktype.text = arraydata.get(position).category.category
        holder.tv_jobcity.text = arraydata.get(position).location
        holder.tv_country.text = arraydata.get(position).country
        holder.tv_pricedetail.text = "$" + arraydata.get(position).priceTo.toString() + "/m"
        holder.img_fav.setOnClickListener {
            clickinterface.onItemClick(position,holder.img_fav)

           

//            if (flag) {
//                holder.img_fav!!.setColorFilter(Color.RED)
//            } else {
//                holder.img_fav!!.setColorFilter(Color.parseColor("#DADADA"))
//            }
//            flag = !flag

        }
        holder.tv_worktype.setOnClickListener {
            var intent = Intent(activity, JobDeatailActivity::class.java)
            intent.putExtra("jobid", arraydata.get(position).id)
            activity.startActivity(intent)
        }

        holder.linearlayout.setOnClickListener {
            var intent = Intent(activity, JobDeatailActivity::class.java)
            intent.putExtra("jobid", arraydata.get(position).id)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
//        return arraydata.size
        return arraydata.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        var _img_userimage: CircleImageView = itemView.findViewById(R.id._img_userimage)
        var img_fav: ImageView = itemView.findViewById(R.id.img_fav)
        var tv_worktype: TextView = itemView.findViewById(R.id.tv_worktype)
        var tv_country: TextView = itemView.findViewById(R.id.tv_country)
        var tv_jobcity: TextView = itemView.findViewById(R.id.tv_jobcity)
        var tv_pricedetail: TextView = itemView.findViewById(R.id.tv_pricedetail)
        var linearlayout: LinearLayout = itemView.findViewById(R.id.linearlayout)

    }


}