package com.app.humanresource.Handler

import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsExamples

interface GetFavJobbyidsHandler {
    fun onSuccess(getFavjobsExample: GetFavjobsExamples?)
    fun onError(message: String?)
}