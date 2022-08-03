package com.app.humanresource.Fragments.chatFragment.Presenter

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Adapter.ChatListRecyclerAdapter
import com.app.humanresource.Fragments.chatFragment.View.chatView
import com.app.humanresource.Handler.GetAllUsersHandler
import com.app.humanresource.Models.GetAllUsersModel.GetAllUser
import com.app.humanresource.Utils.CSPreferences
import com.app.humanresource.Utils.Utils
import com.app.humanresource.Utils.WebServices

class chatPresenter(
    private val activity: FragmentActivity?,
    private val chatView: chatView,
    private val chat_recyclerview: RecyclerView?
) {
    var chatListRecyclerAdapter:ChatListRecyclerAdapter?=null


    fun getAllUsersList() {
        var token  = CSPreferences.readString(activity as FragmentActivity,Utils.TOKEN)
        chatView.showDialog(activity)
        WebServices.getInstance()?.getallusers(token, object : GetAllUsersHandler {
            override fun onSuccess(getAllUser: GetAllUser?) {
                if (getAllUser?.isSuccess == true) {
                    chatView.hideDialog()
                    chat_recyclerview?.layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    chatListRecyclerAdapter = ChatListRecyclerAdapter(activity as FragmentActivity, getAllUser.message)
                    chat_recyclerview?.adapter = chatListRecyclerAdapter
                } else {
                    Toast.makeText(activity, "fail", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onError(message: String?) {
                chatView.hideDialog()

                chatView.showMessage(activity, message)
            }

        })
    }
}