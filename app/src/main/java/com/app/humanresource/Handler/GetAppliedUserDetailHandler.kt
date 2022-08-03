package com.app.humanresource.Handler

import com.app.humanresource.Models.GetApplieduserDetail.GetAppliedUserDetail
import com.app.humanresource.Models.getapplyjobsbyidmodels.GetApplyJobsByIdExamples

interface GetAppliedUserDetailHandler {
    fun onSuccess(getAppliedUserDetail: GetAppliedUserDetail?)
    fun onError(message: String?)
}