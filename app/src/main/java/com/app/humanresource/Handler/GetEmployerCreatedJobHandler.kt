package com.app.humanresource.Handler

import com.app.humanresource.Models.GetEmployerCreatedJob.GetEmployerCreatedJob
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsExamples

interface GetEmployerCreatedJobHandler {
    fun onSuccess(getEmployerCreatedJob: GetEmployerCreatedJob?)
    fun onError(message: String?)
}