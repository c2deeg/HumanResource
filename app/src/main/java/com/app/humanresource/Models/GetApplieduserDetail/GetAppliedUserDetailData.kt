package com.app.humanresource.Models.GetApplieduserDetail

data class GetAppliedUserDetailData(
    val __v: Int,
    val _id: String,
    val country: String,
    val createdOn: String,
    val describe: String,
    val email: String,
    val firstName: String,
    val job: GetAppliedUserDetailJob,
    val lastName: String,
    val postedBy: GetAppliedUserDetailPostedBy,
    val resume: String,
    val status: String,
    val updatedOn: String,
    val user: GetAppliedUserDetailUser
)