package com.app.humanresource.Handler

import com.app.humanresource.Models.GetApplyJobById.GetapplyJobByidExample
import com.app.humanresource.Models.Profile.ProfileExample

interface GetappliedJobsByidHandler {
    fun onSuccess(getapplyJobByidExample: GetapplyJobByidExample?)
    fun onError(message: String?)
}