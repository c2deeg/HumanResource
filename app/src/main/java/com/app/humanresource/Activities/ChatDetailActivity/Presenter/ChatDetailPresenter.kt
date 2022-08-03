package com.app.humanresource.Activities.ChatDetailActivity.Presenter

import androidx.recyclerview.widget.RecyclerView
import com.app.humanresource.Activities.ChatDetailActivity.ChatDetailActivity
import com.app.humanresource.Activities.ChatDetailActivity.View.ChatDetailView
import com.app.humanresource.Adapter.ChatAdapterRecyclerview
import com.app.humanresource.Handler.GetOldChatsHandler
import com.app.humanresource.Models.GetOldChatsModel.GetOldChats
import com.app.humanresource.Utils.ChatApplication.Messages
import com.app.humanresource.Utils.WebServices
import java.util.ArrayList

class ChatDetailPresenter(
    private val activity: ChatDetailActivity,
    private val chatDetailView: ChatDetailView,
    private val messageList: ArrayList<Messages>,

) {


    fun getOldchat(roomId: String) {

        WebServices.getInstance()?.getOldChats(roomId, 1, 100, object : GetOldChatsHandler {
            override fun onSuccess(getOldChats: GetOldChats?) {
                if (getOldChats?.isSuccess == true) {
                    chatDetailView.hideDialog()
                    chatDetailView.showMessage(activity, "success")
                    chatDetailView.setData(activity,getOldChats.items)
//                    for (i in 1..getOldChats.items.size){
//                        addMessage(getOldChats.items.get(i).createdOn,getOldChats.items.get(i).msg,Messages.TYPE_MESSAGE)
//                    }

                } else {
                    chatDetailView.showMessage(activity, "fail")
                }
            }

            override fun onError(message: String?) {
                chatDetailView.hideDialog()
                chatDetailView.showMessage(activity, message)
            }

        })
    }

}