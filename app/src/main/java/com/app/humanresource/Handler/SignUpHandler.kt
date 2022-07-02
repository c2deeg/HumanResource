package com.app.humanresource.Handler

import com.app.humanresource.Models.SignUp.SignUpExample

interface SignUpHandler {
    fun onSuccess(signUpExample: SignUpExample?, acesstoken: String?)
    fun onError(message: String?)
}