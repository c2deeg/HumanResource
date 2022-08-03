package com.app.humanresource.Activities.ChatDetailActivity.View

import android.app.Activity
import com.app.humanresource.Activities.ChatDetailActivity.ChatDetailActivity
import com.app.humanresource.Models.GetOldChatsModel.GetOldChatsItem

interface ChatDetailView {
    fun showMessage(activity: Activity?, msg: String?)
    fun showDialog(activity: Activity?)
    fun hideDialog()
    fun setData(activity: ChatDetailActivity, items: List<GetOldChatsItem>)
}