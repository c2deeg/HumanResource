package com.app.humanresource.Handler

import com.app.humanresource.Models.AddtoFavouriteModels.AddToFavExample
import com.app.humanresource.Models.ApplyJob.ApplyjobExample

interface AddtoFavHandler {
    fun onSuccess(addToFavExample: AddToFavExample?)
    fun onError(message: String?)
}