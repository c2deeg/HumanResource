package com.app.humanresource.Handler

import com.app.humanresource.Models.EditProfile.UploadProfileIMageExample

interface EditProfileHandler {
    fun onSuccess(uploadProfileExample: UploadProfileIMageExample?)
    fun onError(message: String?)
}