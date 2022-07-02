package com.app.humanresource.Handler

import com.app.humanresource.Models.GetPopularjobsModels.PopularJobsExample

interface PopularjobsHandler {
    fun onSuccess(popularJobsExample: PopularJobsExample?)
    fun onError(message: String?)
}