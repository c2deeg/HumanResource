package com.app.humanresource.Models.GetJobApplicants

data class GetJobApplicantsData(
    val applyBy: GetJobApplicantsApplyBy,
    val country: String,
    val createdOn: String,
    val describe: String,
    val email: String,
    val firstName: String,
    val id: String,
    val job: GetJobApplicantsJob,
    val lastName: String,
    val postedBy: GetJobApplicantsPostedBy,
    val resume: String,
    val status: String
)