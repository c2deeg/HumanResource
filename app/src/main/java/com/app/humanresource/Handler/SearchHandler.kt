package com.app.humanresource.Handler

import com.app.humanresource.Models.SearchModels.SearchExamples
import com.app.humanresource.Models.SignUp.SignUpExample

interface SearchHandler {
    fun onSuccess(searchExamples: SearchExamples?)
    fun onError(message: String?)
}