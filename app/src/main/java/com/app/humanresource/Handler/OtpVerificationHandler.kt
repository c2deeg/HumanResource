package com.app.humanresource.Handler

import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.Models.Otp.OtpExample

interface OtpVerificationHandler {
    fun onSuccess(otpExample: OtpExample?)
    fun onError(message: String?)
}