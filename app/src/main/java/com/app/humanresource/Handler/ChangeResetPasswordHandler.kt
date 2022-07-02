package com.app.humanresource.Handler

import com.app.humanresource.Models.ChangePassword.ResetChangePasswordExample

interface ChangeResetPasswordHandler {
    fun onSuccess(changeResetPasswordExample: ResetChangePasswordExample?)
    fun onError(message: String?)
}