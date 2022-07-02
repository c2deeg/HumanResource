package com.app.humanresource.Handler

import com.app.humanresource.Models.CreateCategoryModels.CreateCategoryExample

interface CreateCategoryHandler {
    fun onSuccess(createCategoryExample: CreateCategoryExample?)
    fun onError(message: String?)
}