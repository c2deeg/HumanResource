package com.app.humanresource.Handler

import com.app.humanresource.Models.RecentPostModels.RecentPostsExample

interface RecentPostHandler {
    fun onSuccess(recentPostsExample: RecentPostsExample?)
    fun onError(message: String?)

}