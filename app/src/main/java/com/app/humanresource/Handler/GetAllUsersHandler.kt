package com.app.humanresource.Handler

import com.app.humanresource.Models.GetAllLocationModels.GetallLocationExample
import com.app.humanresource.Models.GetAllUsersModel.GetAllUser

interface GetAllUsersHandler {
    fun onSuccess(getAllUser: GetAllUser?)
    fun onError(message: String?)
}