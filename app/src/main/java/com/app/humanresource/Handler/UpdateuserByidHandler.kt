package com.app.humanresource.Handler

import com.app.humanresource.Models.UpadateuserDataModels.UpdateUserExample

interface UpdateuserByidHandler {
    fun onSuccess(updateUserExample: UpdateUserExample?)
    fun onError(message: String?)
}