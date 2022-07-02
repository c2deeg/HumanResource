package com.app.humanresource.Handler

import com.app.humanresource.Models.GetJobsModels.GetJobsExample

interface GetalljobsHandler {
    fun onSuccess(getJobsExample: GetJobsExample?)
    fun onError(message: String?)
}