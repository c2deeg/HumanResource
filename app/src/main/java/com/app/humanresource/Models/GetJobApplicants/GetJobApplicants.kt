package com.app.humanresource.Models.GetJobApplicants

data class GetJobApplicants(
    val `data`: List<GetJobApplicantsData>,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int
)