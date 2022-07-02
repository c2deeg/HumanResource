package com.app.humanresource.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.JobDeatailActivity.JobDeatailActivity
import com.app.humanresource.Activities.SearchResultActivities.SearchResultActivity
import com.app.humanresource.Models.SearchModels.SearchMessage
import com.app.humanresource.R
import com.app.humanresource.interfaces.AddtoFavInterface

class SearchResultRecyclerviewAdapter(
    private val activity: SearchResultActivity,
    private val data: MutableList<SearchMessage>,
    private val clickinterface: AddtoFavInterface
) :
    RecyclerView.Adapter<SearchResultRecyclerviewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultRecyclerviewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.searchitemresult,parent,false)
        return SearchResultRecyclerviewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SearchResultRecyclerviewAdapter.ViewHolder,
        position: Int
    ) {
        holder.tv_namework.text = data.get(position).title
        holder.tv_pricedetail.text = data.get(position).priceTo.toString()
        holder.tv_jobcity.text = data.get(position).location
        holder.tv_country.text = data.get(position).country
        holder.img_fav.setOnClickListener{
            clickinterface.onItemClick(position,holder.img_fav,data)
        }
        holder.linearlayout.setOnClickListener{
            var intent = Intent(activity, JobDeatailActivity::class.java)
            intent.putExtra("jobid",data.get(position).id)
            activity.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return  data.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tv_namework = itemView.findViewById<TextView>(R.id.tv_namework)
        var tv_pricedetail = itemView.findViewById<TextView>(R.id.tv_pricedetail)
        var tv_jobcity = itemView.findViewById<TextView>(R.id.tv_jobcity)
        var tv_country = itemView.findViewById<TextView>(R.id.tv_country)
        var linearlayout = itemView.findViewById<LinearLayout>(R.id.linearlayout)
        var img_fav = itemView.findViewById<ImageView>(R.id.img_fav)

    }
}