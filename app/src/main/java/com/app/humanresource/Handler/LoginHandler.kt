package com.app.humanresource.Handler

import com.app.humanresource.Models.Login.LoginExample

interface LoginHandler {
    fun onSuccess(loginExample: LoginExample?, acesstoken: String?)
    fun onError(message: String?)

}