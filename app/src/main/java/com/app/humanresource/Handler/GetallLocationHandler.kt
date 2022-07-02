package com.app.humanresource.Handler

import com.app.humanresource.Models.GetAllLocationModels.GetallLocationExample
import com.app.humanresource.Models.GetJobsModels.GetJobsExample

interface GetallLocationHandler {
    fun onSuccess(getallLocationExample: GetallLocationExample?)
    fun onError(message: String?)
}