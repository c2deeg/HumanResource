package com.app.humanresource.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.SearchResultActivities.SearchResultActivity
import com.app.humanresource.Models.ApplyfilterModels.JobFilterDatum
import com.app.humanresource.R

class FilterRecyclerAdapter(
    private val activity: SearchResultActivity,
    private val data: MutableList<JobFilterDatum>
) : RecyclerView.Adapter<FilterRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.filteritemresult,parent,false)
        return FilterRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterRecyclerAdapter.ViewHolder, position: Int) {
        holder.tv_namework.text = data.get(position).title
        holder.tv_pricedetail.text = data.get(position).priceTo.toString()
        holder.tv_jobcity.text = data.get(position).location
        holder.tv_country.text = data.get(position).country
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tv_namework = itemView.findViewById<TextView>(R.id.tv_namework)
        var tv_pricedetail = itemView.findViewById<TextView>(R.id.tv_pricedetail)
        var tv_jobcity = itemView.findViewById<TextView>(R.id.tv_jobcity)
        var tv_country = itemView.findViewById<TextView>(R.id.tv_country)

    }
}