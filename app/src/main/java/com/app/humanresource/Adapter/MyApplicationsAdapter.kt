package com.app.humanresource.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.MyApplicationsActivity.MyApplicationsActivity
import com.app.humanresource.Models.GetApplyJobById.GetapplyJobByidDatum
import com.app.humanresource.R
import android.R.string
import com.app.humanresource.Models.getapplyjobsbyidmodels.GetApplyJobsByIdDatum


class MyApplicationsAdapter(
    private val activity: MyApplicationsActivity,
    private val data: MutableList<GetApplyJobsByIdDatum>
) : RecyclerView.Adapter<MyApplicationsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyApplicationsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.myapplicationsitem,parent,false)
        return MyApplicationsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyApplicationsAdapter.ViewHolder, position: Int) {

        holder.tv_technologytittle?.text = data.get(position).job.title
        holder.tv_companyname?.text = data.get(position).job.company.emailId
        holder.tv_location?.text = data.get(position).job.location + ","+ data.get(position).country
        var result = data.get(position).createdOn.dropLast(14)

        holder.tv_applieddate?.text = "Apllied on " +result

    }

    override fun getItemCount(): Int {
       return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tv_technologytittle: TextView = itemView.findViewById(R.id.tv_technologytittle)
        var tv_companyname: TextView = itemView.findViewById(R.id.tv_companyname)
        var tv_location: TextView = itemView.findViewById(R.id.tv_location)
        var tv_applieddate: TextView = itemView.findViewById(R.id.tv_applieddate)
        var tv_statusaboutselection: TextView = itemView.findViewById(R.id.tv_statusaboutselection)

    }
}