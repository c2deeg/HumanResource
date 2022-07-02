package com.app.humanresource.Handler

import com.app.humanresource.Models.ApplyJob.UploadPdf.UploadPdfExample

interface UploadPdfHandler {
    fun onSuccess(uploadPdfExample: UploadPdfExample?)
    fun onError(message: String?)
}