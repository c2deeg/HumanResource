package com.app.humanresource.Models.GetOldChatsModel

data class GetOldChats(
    val isSuccess: Boolean,
    val items: List<GetOldChatsItem>,
    val pageNo: Int,
    val pageSize: Int,
    val statusCode: Int,
    val total: Int
)