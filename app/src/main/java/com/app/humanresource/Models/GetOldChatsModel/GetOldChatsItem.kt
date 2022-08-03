package com.app.humanresource.Models.GetOldChatsModel

data class GetOldChatsItem(
    val createdOn: String,
    val fromMsg: String,
    val id: String,
    val msg: String,
    val room: String,
    val toMsg: String
)