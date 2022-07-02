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
import com.app.humanresource.Models.RecentPostModels.RecentPostsMessage
import com.app.humanresource.R
import com.app.humanresource.interfaces.Popularjobsclickinterfaces
import de.hdodenhof.circleimageview.CircleImageView

class RecentPostsRecyclerAdapter(
    private val activity: FragmentActivity,
    private val data: MutableList<RecentPostsMessage>,
    private val clickinterface:Popularjobsclickinterfaces
):
    RecyclerView.Adapter<RecentPostsRecyclerAdapter.ViewHolder>() {
    var flag = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPostsRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recentpostitem,parent,false)
        return RecentPostsRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentPostsRecyclerAdapter.ViewHolder, position: Int) {
        holder.tv_nameconstructionwork.text = data.get(position).category.category
        holder.tv_pricedetail.text = data.get(position).priceTo.toString()
        holder.tv_jobcity.text = data.get(position).location
        holder.tv_country.text = data.get(position).country

        holder.img_fav.setOnClickListener {
            clickinterface.onItemClick(position,holder.img_fav)
            if (flag) {
                holder.img_fav!!.setColorFilter(Color.RED)
            } else {
                holder.img_fav!!.setColorFilter(Color.parseColor("#DADADA"))
            }
            flag = !flag

        }
        holder.tv_nameconstructionwork.setOnClickListener {
            var intent  = Intent(activity, JobDeatailActivity::class.java)
            intent.putExtra("jobid",data.get(position).id)
            activity.startActivity(intent)
        }

        holder.linearlayout.setOnClickListener {
            var intent  = Intent(activity, JobDeatailActivity::class.java)
            intent.putExtra("jobid",data.get(position).id)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var userimage: CircleImageView = itemView.findViewById(R.id.userimage)
        var img_fav: ImageView = itemView.findViewById(R.id.img_fav)
        var tv_nameconstructionwork: TextView = itemView.findViewById(R.id.tv_nameconstructionwork)
        var tv_pricedetail: TextView = itemView.findViewById(R.id.tv_pricedetail)
        var tv_jobcity: TextView = itemView.findViewById(R.id.tv_jobcity)
        var tv_country: TextView = itemView.findViewById(R.id.tv_country)
        var tv_timehour: TextView = itemView.findViewById(R.id.tv_timehour)
        var linearlayout: LinearLayout = itemView.findViewById(R.id.linearlayout)

    }


}