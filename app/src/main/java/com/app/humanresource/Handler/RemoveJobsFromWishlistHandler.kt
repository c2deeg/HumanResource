package com.app.humanresource.Handler

import com.app.humanresource.Models.RemoveJobsFromWishlist.RemoveJobsFromFav

interface RemoveJobsFromWishlistHandler {
    fun onSuccess(removeJobsFromWishlist: RemoveJobsFromFav?)
    fun onError(message: String?)
}