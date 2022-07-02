package com.app.humanresource.Handler

import com.app.humanresource.Models.ForogotPassword.ForgotPasswordExample

interface ForgotPasswordHandler {
    fun onSuccess(forgotPasswordExample: ForgotPasswordExample?)
    fun onError(message: String?)
}