package com.app.humanresource.Handler

import com.app.humanresource.Models.ApplyJob.ApplyjobExample
import com.app.humanresource.Models.ApplyfilterModels.JobFilterExamples

interface ApplyFilterHandler {
    fun onSuccess(jobFilterExamples: JobFilterExamples?)
    fun onError(message: String?)
}