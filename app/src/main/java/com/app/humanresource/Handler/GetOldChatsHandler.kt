package com.app.humanresource.Handler

import com.app.humanresource.Models.GetJobById.GetJobByIdExample
import com.app.humanresource.Models.GetOldChatsModel.GetOldChats

interface GetOldChatsHandler{
     fun onSuccess(getOldChatsHandler: GetOldChats?)
     fun onError(message: String?)
 }