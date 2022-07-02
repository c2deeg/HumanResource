package com.app.humanresource.Utils

import com.app.humanresource.Models.AddtoFavouriteModels.AddToFavExample
import com.app.humanresource.Models.ApplyJob.ApplyjobExample
import com.app.humanresource.Models.ApplyJob.UploadPdf.UploadPdfExample
import com.app.humanresource.Models.ApplyfilterModels.JobFilterExamples
import com.app.humanresource.Models.ChangePassword.ResetChangePasswordExample
import com.app.humanresource.Models.CreateCategoryModels.CreateCategoryExample
import com.app.humanresource.Models.Createjob.CreateobsExamples
import com.app.humanresource.Models.ForogtoChangePasword.ChangePasswordExample
import com.app.humanresource.Models.EditProfile.UpdateUserProfile.UpdateUserInfoExample
import com.app.humanresource.Models.EditProfile.UploadProfileIMageExample
import com.app.humanresource.Models.ForogotPassword.ForgotPasswordExample
import com.app.humanresource.Models.GetAllLocationModels.GetallLocationExample
import com.app.humanresource.Models.GetApplyJobById.GetapplyJobByidExample
import com.app.humanresource.Models.GetEmployerCreatedJob.GetEmployerCreatedJob
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsExamples
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicants
import com.app.humanresource.Models.GetJobById.GetJobByIdExample
import com.app.humanresource.Models.GetJobsModels.GetJobsExample
import com.app.humanresource.Models.GetPopularjobsModels.PopularJobsExample
import com.app.humanresource.Models.GetallCategoryExample.GetallCategoryExample
import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.Models.Otp.OtpExample
import com.app.humanresource.Models.Profile.ProfileExample
import com.app.humanresource.Models.RecentPostModels.RecentPostsExample
import com.app.humanresource.Models.SearchModels.SearchExamples
import com.app.humanresource.Models.SignUp.SignUpExample
import com.app.humanresource.Models.UpadateuserDataModels.UpdateUserExample
import com.app.humanresource.Models.getapplyjobsbyidmodels.GetApplyJobsByIdExamples
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface API {


    //SignUpAPI
    @POST("users/register")
        fun signupAPI(@Body jsonObject: JsonObject?): Call<SignUpExample>

    //LoginAPI
    @POST("users/login")
    fun logAPI(@Body jsonObject: JsonObject?): Call<LoginExample?>?

    //forgotPasswordApi
    @POST("users/forgotPassword")
    fun forgotpassAPI(@Body jsonObject: JsonObject?): Call<ForgotPasswordExample>?


    //uploadProfileImage
    @Multipart
    @POST("users/uploadImage")
    fun uploadProfileImageAPI(
        @Part("id") description: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<UploadProfileIMageExample>

    //GetUserData
    @GET("users/currentUser/{id}")
    fun getCurrentUserId(
        @Header("x-access-token") token: String,
        @Path("id") id: String
    ): Call<ProfileExample>

    //UploadPdfFile
    @Multipart
    @PUT("jobs/uploadDocs/{id}")
    fun uploadPdfFileAPI(
        @Part file: MultipartBody.Part, @Path("id") id: String
    ): Call<UploadPdfExample>

    //updateuserprofile
    @PUT("users/update/{id}")
    fun upadateProfileInfoAPI(
        @Header("x-access-token") token: String,
        @Path("id") id: String,
        jsonObject: JsonObject?
    ): Call<UpdateUserInfoExample>

    //ForgotChangePasswordExample
    @PUT("users/newPassword")
    fun changePasswordAPI(
        @Header("x-access-token") token: String,
        @Body jsonObject: JsonObject?
    ): Call<ChangePasswordExample>

    //changeResetPassword
    @PUT("users/changePassword/{id}")
    fun changeResetPasswordAPI(
        @Header("x-access-token") token: String,
        @Path("id") id: String,
        @Body jsonObject: JsonObject?
    ): Call<ResetChangePasswordExample>

    //OtpAPI
    @POST("users/otpVerifyAndChangePassword")
    fun OtpAPI(
        @Header("x-access-token") token: String,
        @Body jsonObject: JsonObject?
    ): Call<OtpExample>

    //createjobapi
    @POST("jobs/create")
    fun createjobAPI(@Body jsonObject: JsonObject?): Call<CreateobsExamples>

    //getalljobsapi
    @GET("jobs/getAllJobs")
    fun getAllJobs(): Call<GetJobsExample>

    //recentpostapi
    @GET("jobs/recentPosts")
    fun recentPostAPI(): Call<RecentPostsExample>

    //getpopularjobs
    @GET("jobs/getpopularjobs")
    fun getPopularjobs(): Call<PopularJobsExample>

    //getjobnyId
    @GET("jobs/getJobs/{id}")
    fun getjobbyId(@Path("id") id: String): Call<GetJobByIdExample>

    //updateuserbyid
    @PUT("users/update/{id}")
    fun updateuserbyIdAPI(
        @Header("x-access-token") token: String,
        @Path("id") id: String,
        @Body jsonObject: JsonObject?
    ): Call<UpdateUserExample>

    @POST("category/create")
    fun createCategoryAPI(@Body jsonObject: JsonObject?): Call<CreateCategoryExample>

    //applyjob
    @POST("apply/create")
    fun applyjobs(@Body jsonObject: JsonObject?): Call<ApplyjobExample>

    //getcategoryarrayforspinner
    @GET("category/getAllCategory")
    fun getallcategory(): Call<GetallCategoryExample>

    //getalllocationforspinner
    @GET("jobs/getAllLocation")
    fun getAlllocationAPI(): Call<GetallLocationExample>

    //addtofav
    @POST("wishlist/create")
    fun addtofavAPI(@Body jsonObject: JsonObject?): Call<AddToFavExample>

    @GET("apply/getJobsApply/{id}")
    fun getapplyjobbyid(@Path("id") id: String): Call<GetapplyJobByidExample>

    //getfavjobsbyid__________________________________________________________________________________________________
    @GET("wishlist/wishlistByUserId/{id}")
    fun getfavjobsByIdAPI(@Path("id") id: String): Call<GetFavjobsExamples>

    //applyfilter_____________________________________________________________________________________________________
    @POST("jobs/jobsFilter")
    fun applyFilterAPI(
        @Query("category") category: String, @Query("jobType") jobType: String,
        @Query("location") location: String, @Query("priceFrom") priceFrom: String,
        @Query("priceTo") priceTo: String
    ):Call<JobFilterExamples>
    //searchapi
    @GET("jobs/search")
    fun searchAPI(@Query("title")title:String):Call<SearchExamples>
    //getapplyjobsbyid____________________________________________________________________________________________________________
    @GET("apply/listByUserId/{id}")
    fun getappliedjobbyid(@Path("id")id: String):Call<GetApplyJobsByIdExamples>
    //getemployercreatedByid________________________________________________________________________________________________________________
    @GET("jobs/getJobsByuserId/{id}")
    fun getemployercreateJob(@Path("id")userid:String):Call<GetEmployerCreatedJob>
    //getjobapplicants__________________________________________________________________________________________________________________________________
    @GET("apply/listByJobId/{id}")
    fun getjobapplicants(@Path("id")jobid:String):Call<GetJobApplicants>

}
