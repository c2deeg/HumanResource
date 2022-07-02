package com.app.humanresource.Fragments.chatFragment

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Adapter.ChatListRecyclerAdapter
import com.app.humanresource.R
import androidx.recyclerview.widget.ItemTouchHelper

import com.google.android.material.snackbar.Snackbar

import com.app.humanresource.StaticModels.ChatListModelClass

import com.app.humanresource.Utils.SwipeToDeleteCallback as SwipeToDeleteCallback1


// TODO: Rename parameter arguments, choose names that match
class chatFragment : Fragment() {
    var activity:Activity?=null
    var chat_recyclerview:RecyclerView?=null
    var chatListRecyclerAdapter:ChatListRecyclerAdapter?=null
    var linear:LinearLayout?=null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view  =  inflater.inflate(R.layout.fragment_chat, container, false)
        activity = getActivity()
        init(view)
        listeners(view)

        var chatList: ArrayList<ChatListModelClass> = ArrayList()
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jack", "2.40pm", "Hii Jack how are you"))
        chatList.add(ChatListModelClass(R.drawable.imggirl2, "Jacky Chen", "2.40pm", "Hii man "))
        chatList.add(ChatListModelClass(R.drawable.putin, " Orton", "2.40pm", "Hii sir "))
        chatList.add(ChatListModelClass(R.drawable.imggirl3, "Shea", "2.40pm", "After five mins i will call you"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))
        chatList.add(ChatListModelClass(R.drawable.imggirl, "Jac", "2.40pm", "Hey man"))


        chat_recyclerview?.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        chatListRecyclerAdapter = ChatListRecyclerAdapter(activity as FragmentActivity,chatList)
        chat_recyclerview?.adapter = chatListRecyclerAdapter
        enableSwipeToDeleteAndUndo()

        return view
    }

    private fun init(view: View) {
        chat_recyclerview = view.findViewById(R.id.chat_recyclerview)
        linear = view.findViewById(R.id.linear)
    }

    private fun listeners(view: View?) {

    }

  private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback1 = object : SwipeToDeleteCallback1(activity)
        {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item: ChatListModelClass = chatListRecyclerAdapter?.getData()!!?.get(position)!!
                chatListRecyclerAdapter!!.removeItem(position)
                val snackbar = Snackbar
                    .make(
                        linear!!,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
//                snackbar.setAction("UNDO") {
//                    chatListRecyclerAdapter.restoreItem(item, position)
//                    chat_recyclerview?.scrollToPosition(position)
//                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(chat_recyclerview)
    }


}