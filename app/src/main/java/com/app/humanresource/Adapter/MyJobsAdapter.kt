package com.app.humanresource.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyJobsActivity.MyJobsActivity
import com.app.humanresource.Activities.MyapplicantsActivity.MyapplicantsActivity
import com.app.humanresource.Models.GetEmployerCreatedJob.GetEmployerCreatedJobData
import com.app.humanresource.R

class MyJobsAdapter(private val activity: MyJobsActivity,private val data: List<GetEmployerCreatedJobData>) : RecyclerView.Adapter<MyJobsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyJobsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.myjobslist,parent,false)
        return MyJobsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyJobsAdapter.ViewHolder, position: Int) {
        holder.tv_nameconstructionwork.text = data?.get(position)?.title
        holder.tv_pricedetail.text = data?.get(position)?.priceTo.toString()
        holder.tv_jobcity.text = data?.get(position)?.location
        holder.tv_country.text = data?.get(position)?.country
        holder.linear.setOnClickListener{
            var intent = Intent(activity,MyapplicantsActivity::class.java)
            intent.putExtra("jobid",data.get(position)._id)
            activity.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var linear = itemView.findViewById<LinearLayout>(R.id.linear)
        var tv_nameconstructionwork = itemView.findViewById<TextView>(R.id.tv_nameconstructionwork)
        var tv_pricedetail = itemView.findViewById<TextView>(R.id.tv_pricedetail)
        var tv_country = itemView.findViewById<TextView>(R.id.tv_country)
        var tv_jobcity = itemView.findViewById<TextView>(R.id.tv_jobcity)

    }
}