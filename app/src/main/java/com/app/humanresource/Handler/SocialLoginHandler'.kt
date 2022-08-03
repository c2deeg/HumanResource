package com.app.humanresource.Handler

import com.app.humanresource.Models.SearchModels.SearchExamples
import com.app.humanresource.Models.SocialLogin.GoogleSignInModel

interface SocialLoginHandler {
    fun onSuccess(googleSignInModel: GoogleSignInModel?, acesstoken: String?)
    fun onError(message: String?)
}