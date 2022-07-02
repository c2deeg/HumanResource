package com.app.humanresource.Models.GetJobApplicants

data class GetJobApplicantsJob(
    val __v: Int,
    val _id: String,
    val category: String,
    val company: GetJobApplicantsCompany,
    val constructionDocumentation: String,
    val country: String,
    val createdOn: String,
    val email: String,
    val firstName: String,
    val jobType: String,
    val lastName: String,
    val location: String,
    val planOfAction: String,
    val priceFrom: Int,
    val priceTo: Int,
    val scopeOfWork: String,
    val title: String,
    val updatedOn: String,
    val user: String,
    val workers: Int
)