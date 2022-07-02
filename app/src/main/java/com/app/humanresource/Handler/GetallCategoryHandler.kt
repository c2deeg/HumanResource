package com.app.humanresource.Handler

import com.app.humanresource.Models.GetJobsModels.GetJobsExample
import com.app.humanresource.Models.GetallCategoryExample.GetallCategoryExample

interface GetallCategoryHandler {
    fun onSuccess(getallCategoryExample: GetallCategoryExample?)
    fun onError(message: String?)
}