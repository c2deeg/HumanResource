package com.app.humanresource.Handler

import com.app.humanresource.Models.Profile.ProfileExample
import com.app.humanresource.Models.getapplyjobsbyidmodels.GetApplyJobsByIdExamples

interface GetAppliedjobsbyUseridHandler {
    fun onSuccess(getApplyJobsByIdExamples: GetApplyJobsByIdExamples?)
    fun onError(message: String?)
}