package com.app.humanresource.Handler

import com.app.humanresource.Models.ForogtoChangePasword.ChangePasswordExample

interface ChangePasswordHandler {
    fun onSuccess(changePasswordExample: ChangePasswordExample?)
    fun onError(message: String?)
}