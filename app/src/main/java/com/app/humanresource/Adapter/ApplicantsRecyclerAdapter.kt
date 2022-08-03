package com.app.humanresource.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.ApplicantsDetailActivity.ApplicantsDetailActivity
import com.app.humanresource.Activities.ApplicantsDetailActivity.View.ApplicantsDetailView
import com.app.humanresource.Activities.MyapplicantsActivity.MyapplicantsActivity
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicantsData
import com.app.humanresource.R

class ApplicantsRecyclerAdapter(
    private val activity: MyapplicantsActivity,
    private val data: List<GetJobApplicantsData>
) :
    RecyclerView.Adapter<ApplicantsRecyclerAdapter.ViewHolder>() {
    var applicationsView:ApplicantsDetailView?=null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplicantsRecyclerAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.myapplicantsitem, parent, false)
        return ApplicantsRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplicantsRecyclerAdapter.ViewHolder, position: Int) {
        holder.tv_applicantname.text = data.get(position).firstName + "" + data.get(position).lastName
        holder.tv_date.text = data?.get(position)?.createdOn?.dropLast(14)
        holder.tv_phonenumber.text = data?.get(position)?.applyBy?.phoneNumber


        holder.linear.setOnClickListener {
            var intent = Intent(activity,ApplicantsDetailActivity::class.java)
            intent.putExtra("jobid",data.get(position).id)
            intent.putExtra("userid",data.get(position).applyBy._id)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tv_applicantname: TextView = itemView.findViewById(R.id.tv_applicantname)
        var tv_date: TextView = itemView.findViewById(R.id.tv_date)
        var tv_phonenumber: TextView = itemView.findViewById(R.id.tv_phonenumber)
        var linear: LinearLayout = itemView.findViewById(R.id.linear)


    }
}