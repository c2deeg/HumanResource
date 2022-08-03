package com.app.humanresource.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.R
import android.R.attr.data
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import com.app.humanresource.Activities.ChatDetailActivity.ChatDetailActivity
import com.app.humanresource.Activities.ChatMessageActivity.ChatMessageActivity
import com.app.humanresource.Models.GetAllUsersModel.Message
import com.app.humanresource.StaticModels.ChatListModelClass


class ChatListRecyclerAdapter(
    private val activity: FragmentActivity?,
    private val chatList: List<Message>
):
    RecyclerView.Adapter<ChatListRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListRecyclerAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.chatlistitem,parent,false)
        return ChatListRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatListRecyclerAdapter.ViewHolder, position: Int) {
        holder.tv_name.text = chatList.get(position).userName
//        holder.tv_time.text = chatList.get(position).userName
//        holder.tv_name.text =mList.name
//        holder.tv_message.text = mList.message
//        holder.tv_time.text = mList.time
        holder.tv_name.setOnClickListener{
            var intent = Intent(activity,ChatDetailActivity::class.java)
            intent.putExtra("username",chatList.get(position).userName)
            activity?.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
   return chatList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val userimage: ImageView = itemView.findViewById(R.id.userimage)
        val tv_message: TextView = itemView.findViewById(R.id.tv_message)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
        val tv_time: TextView = itemView.findViewById(R.id.tv_time)

    }

    fun removeItem(position: Int) {
//        chatList.removeAt(position)
        notifyItemRemoved(position)

    }

//    fun restoreItem(item1: ChatListModelClass?, position: Int) {
//        chatList.add(position, item1)
//        notifyItemInserted(position)
//    }

//    fun getData(): ArrayList<ChatListModelClass> {
//        return chatList
//    }
}