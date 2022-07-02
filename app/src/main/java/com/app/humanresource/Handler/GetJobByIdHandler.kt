package com.app.humanresource.Handler

import com.app.humanresource.Models.GetJobById.GetJobByIdExample

interface GetJobByIdHandler {
    fun onSuccess(getJobByIdExample: GetJobByIdExample?)
    fun onError(message: String?)
}