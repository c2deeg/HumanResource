package com.app.humanresource.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.R
import com.app.humanresource.StaticModels.JobTypeRecyclerModelClass
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils

class JobTypeRecyclerViewAdapter(
    private val activity: FragmentActivity?,
    private val data: ArrayList<JobTypeRecyclerModelClass>
) :
    RecyclerView.Adapter<JobTypeRecyclerViewAdapter.ViewHolder>() {
    private var row_index = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JobTypeRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.jobtypeitem,parent,false)
        return JobTypeRecyclerViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobTypeRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.tv_jobtype.text = data.get(position).jobtype
        holder.jobtype_linear.setOnClickListener{
            row_index = position
            notifyDataSetChanged()
        }

        if (row_index == position) {
            holder.tv_jobtype?.setTextColor(Color.BLACK)
            holder.tv_jobtype?.setBackgroundDrawable(activity?.resources?.getDrawable(R.drawable.appgoldenbackground))
            Toast.makeText(activity, holder.tv_jobtype.getText(), Toast.LENGTH_SHORT).show()
            CSPreferences.putString(activity!!,Utils.BOOLEANVALUE,
                holder.tv_jobtype.getText() as String?
            )
        } else {
            holder.tv_jobtype?.setTextColor(Color.WHITE)
            holder.tv_jobtype?.setBackgroundDrawable(activity?.resources?.getDrawable(R.drawable.stroke_roundcorner))
        }

    }

    override fun getItemCount(): Int {
       return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tv_jobtype:TextView = itemView.findViewById(R.id.tv_jobtype)
        var jobtype_linear:LinearLayout = itemView.findViewById(R.id.jobtype_linear)

    }


}