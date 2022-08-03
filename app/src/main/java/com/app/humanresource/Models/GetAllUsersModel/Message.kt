package com.app.humanresource.Models.GetAllUsersModel

data class Message(
    val __v: Int,
    val _id: String,
    val companyName: String,
    val email: String,
    val image: String,
    val password: String,
    val phoneNumber: String,
    val roleType: String,
    val userName: String
)