package com.app.humanresource.Handler

import com.app.humanresource.Models.ApplyJob.ApplyjobExample

interface ApplyJobsHandler {
    fun onSuccess(changePasswordExample: ApplyjobExample?)
    fun onError(message: String?)
}