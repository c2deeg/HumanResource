package com.app.humanresource.Handler

import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsExamples
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicants

interface GetJobApplicantsHandler {
    fun onSuccess(getJobApplicants: GetJobApplicants?)
    fun onError(message: String?)
}