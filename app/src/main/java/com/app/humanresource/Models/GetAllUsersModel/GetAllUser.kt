package com.app.humanresource.Models.GetAllUsersModel

data class GetAllUser(
    val isSuccess: Boolean,
    val message: List<Message>,
    val statusCode: Int
)