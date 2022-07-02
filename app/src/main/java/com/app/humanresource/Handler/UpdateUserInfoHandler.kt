package com.app.humanresource.Handler

import com.app.humanresource.Models.EditProfile.UpdateUserProfile.UpdateUserInfoExample

interface UpdateUserInfoHandler {

    fun onSuccess(updateUserInfoExample: UpdateUserInfoExample?)
    fun onError(message: String?)
}