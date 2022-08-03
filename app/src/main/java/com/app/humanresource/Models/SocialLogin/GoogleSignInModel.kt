package com.app.humanresource.Models.SocialLogin

data class GoogleSignInModel(
    val `data`: GoogleSignInModelData,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int
)