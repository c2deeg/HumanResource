package com.app.humanresource.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.AllJobsDetailActivity.AllJobsDetailActivity
import com.app.humanresource.Activities.JobDeatailActivity.JobDeatailActivity
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsDatum
import com.app.humanresource.R

class FavouritesRecyclerAdapter(
    private val activity: FragmentActivity?,
   private val data: MutableList<GetFavjobsDatum>
) : RecyclerView.Adapter<FavouritesRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesRecyclerAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.favouriteslist,parent,false)
        return FavouritesRecyclerAdapter.ViewHolder(view)

    }

    override fun onBindViewHolder(holder: FavouritesRecyclerAdapter.ViewHolder, position: Int) {
        holder.tv_name.text = data.get(position).jobId.title
        holder.tv_jobtype.text = data.get(position).jobId.jobType
        holder.linear.setOnClickListener {
            var intent = Intent(activity,JobDeatailActivity::class.java)
            intent.putExtra("jobid",data.get(position).jobId.id)
            activity?.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
       return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tv_name = itemView.findViewById<TextView>(R.id.tv_name)
        var tv_jobtype = itemView.findViewById<TextView>(R.id.tv_jobtype)
        var linear = itemView.findViewById<LinearLayout>(R.id.linear)

    }
}