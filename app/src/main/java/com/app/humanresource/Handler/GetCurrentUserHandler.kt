package com.app.humanresource.Handler

import com.app.humanresource.Models.Profile.ProfileExample

interface GetCurrentUserHandler {
    fun onSuccess(getUserExample: ProfileExample?)
    fun onError(message: String?)
}