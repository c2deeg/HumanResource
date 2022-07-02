package com.app.humanresource.Models.GetEmployerCreatedJob

data class GetEmployerCreatedJob(
    val `data`: List<GetEmployerCreatedJobData>,
    val isSuccess: Boolean,
    val message: String,
    val statusCode: Int
)