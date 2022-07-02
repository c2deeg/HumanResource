package com.app.humanresource.Handler

import com.app.humanresource.Models.Createjob.CreateobsExamples

interface CreatejobHandler {
    fun onSuccess(createjobExample: CreateobsExamples?)
    fun onError(message: String?)
}