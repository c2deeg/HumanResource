package com.app.humanresource.Models.GetApplieduserDetail

data class GetAppliedUserDetail(
    val `data`: GetAppliedUserDetailData,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int
)