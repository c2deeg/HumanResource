package com.app.humanresource.Utils

import com.app.humanresource.Handler.*
import com.app.humanresource.Models.AddtoFavouriteModels.AddToFavExample
import com.app.humanresource.Models.ApplyJob.ApplyjobExample
import com.app.humanresource.Models.ApplyJob.UploadPdf.UploadPdfExample
import com.app.humanresource.Models.ApplyfilterModels.JobFilterExamples
import com.app.humanresource.Models.ChangePassword.ResetChangePasswordExample
import com.app.humanresource.Models.CreateCategoryModels.CreateCategoryExample
import com.app.humanresource.Models.Createjob.CreateobsExamples
import com.app.humanresource.Models.ForogtoChangePasword.ChangePasswordExample
import com.app.humanresource.Models.EditProfile.UploadProfileIMageExample
import com.app.humanresource.Models.ForogotPassword.ForgotPasswordExample
import com.app.humanresource.Models.GetAllLocationModels.GetallLocationExample
import com.app.humanresource.Models.GetAllUsersModel.GetAllUser
import com.app.humanresource.Models.GetApplieduserDetail.GetAppliedUserDetail
import com.app.humanresource.Models.GetApplyJobById.GetapplyJobByidExample
import com.app.humanresource.Models.GetEmployerCreatedJob.GetEmployerCreatedJob
import com.app.humanresource.Models.GetFavjobsModel.GetFavjobsExamples
import com.app.humanresource.Models.GetJobApplicants.GetJobApplicants
import com.app.humanresource.Models.GetJobById.GetJobByIdExample
import com.app.humanresource.Models.GetJobsModels.GetJobsExample
import com.app.humanresource.Models.GetOldChatsModel.GetOldChats
import com.app.humanresource.Models.GetPopularjobsModels.PopularJobsExample
import com.app.humanresource.Models.GetallCategoryExample.GetallCategoryExample
import com.app.humanresource.Models.Login.LoginExample
import com.app.humanresource.Models.Otp.OtpExample
import com.app.humanresource.Models.Profile.ProfileExample
import com.app.humanresource.Models.RecentPostModels.RecentPostsExample
import com.app.humanresource.Models.RemoveJobsFromWishlist.RemoveJobsFromFav
import com.app.humanresource.Models.SearchModels.SearchExamples
import com.app.humanresource.Models.SignUp.SignUpExample
import com.app.humanresource.Models.SocialLogin.GoogleSignInModel
import com.app.humanresource.Models.UpadateuserDataModels.UpdateUserExample
import com.app.humanresource.Models.getapplyjobsbyidmodels.GetApplyJobsByIdExamples
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebServices {
    private val TAG = "WebSrvices"
    private lateinit var api: API


    fun create() {
        retrofit =
            Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()

        api = retrofit.create(API::class.java)

    }


    companion object Factory1 {
        private lateinit var mInstance: WebServices

        private lateinit var retrofit: Retrofit

        val base_url = "http://34.231.88.85:8001/api/"

        //        val base_url = "http://93.188.167.68:8004/api/"
        internal var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()


        fun getInstance(): WebServices? {
            mInstance = WebServices()
            return mInstance
        }
    }

    fun apiCreate() {
        api = retrofit.create(API::class.java)
    }


    //SignupMethod
    fun signupMethod(
        userName: String?,
        email: String?,
        companyName: String?,
        password: String?,
        roleType: String?,
        phoneNumber: String?,
        signUpHandler: SignUpHandler
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("userName", userName)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("companyName", companyName)
        jsonObject.addProperty("password", password)
        jsonObject.addProperty("roleType", roleType)
        jsonObject.addProperty("phoneNumber", phoneNumber)
        apiCreate()
        api?.signupAPI(jsonObject)?.enqueue(object : Callback<SignUpExample> {
            override fun onResponse(
                call: Call<SignUpExample?>,
                response: Response<SignUpExample?>
            ) {
                if (response.code() == 200) {
                    signUpHandler.onSuccess(response.body(), acesstoken = null)
                } else {
                    val responceData =
                        SocketConnection.convertStreamToString(response.errorBody()!!.byteStream())
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            signUpHandler.onError(message)
                        } else {
                            signUpHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<SignUpExample?>, t: Throwable) {
                signUpHandler.onError(t.message)
            }

        })
    }

    //LoginMethod
    fun loginMethod(email: String?, password: String?, token: String?, loginHandler: LoginHandler) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("password", password)
        jsonObject.addProperty("deviceToken", token)

        apiCreate()
        api?.logAPI(jsonObject)?.enqueue(object : Callback<LoginExample?> {
            override fun onResponse(call: Call<LoginExample?>, response: Response<LoginExample?>) {
                var acesstoken: String? = null
                acesstoken = response.headers()["x-access-token"]
                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body(), acesstoken)
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            loginHandler.onError(message)
                        } else {
                            loginHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<LoginExample?>, t: Throwable) {
                loginHandler.onError(t.message)
            }
        })
    }


    //forgotPasswordMethod
    fun forgotPasswordMethod(email: String?, forgotPasswordHandler: ForgotPasswordHandler) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("email", email)
        apiCreate()
        api?.forgotpassAPI(jsonObject)?.enqueue(object : Callback<ForgotPasswordExample?> {
            override fun onResponse(
                call: Call<ForgotPasswordExample?>,
                response: Response<ForgotPasswordExample?>
            ) {
                if (response.code() == 200) {
                    forgotPasswordHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            forgotPasswordHandler.onError(message)
                        } else {
                            forgotPasswordHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ForgotPasswordExample?>, t: Throwable) {
                forgotPasswordHandler.onError(t.message)
            }

        })
    }

    //otpVerificationAPI
    fun otpVerificationMethod(
        token: String?,
        otp: String?,
        otpVerificationHandler: OtpVerificationHandler
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("otp", otp)
        apiCreate()
        api.OtpAPI(token!!, jsonObject)!!.enqueue(object : Callback<OtpExample> {
            override fun onResponse(call: Call<OtpExample>, response: Response<OtpExample>) {
                if (response.code() == 200) {
                    otpVerificationHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            otpVerificationHandler.onError(message)
                        } else {
                            otpVerificationHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<OtpExample>, t: Throwable) {
                otpVerificationHandler.onError(t.message)
            }

        }
        )
    }

    //setForgotPasswordAPI
//    fun setForgotPassordMethod(
//        token: String?,
//        password: String?,
//        setNewForgotPasswordHandler: SetNewForgotPasswordHandler
//    ) {
//        val jsonObject = JsonObject()
//        jsonObject.addProperty("newPassword", password)
//        apiCreate()
//        api.setForgotPasswordAPI(token!!, jsonObject).enqueue(object : Callback<LoginExample> {
//            override fun onResponse(call: Call<LoginExample>, response: Response<LoginExample>) {
//                if (response.code() == 200) {
//                    setNewForgotPasswordHandler.onSuccess(response.body())
//                } else {
//                    val responceData = SocketConnection.convertStreamToString(
//                        response.errorBody()!!.byteStream()
//                    )
//                    try {
//                        val jsonObject = JSONObject(responceData)
//                        val message = jsonObject.optString("message")
//                        val error = jsonObject.optString("error")
//                        if (!message.equals("", ignoreCase = true)) {
//                            setNewForgotPasswordHandler.onError(message)
//                        } else {
//                            setNewForgotPasswordHandler.onError(error)
//                        }
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<LoginExample>, t: Throwable) {
//                setNewForgotPasswordHandler.onError(t.message)
//            }
//        })
//    }
    //uploadProfileImage
    fun uploadProfileImage(
        id: RequestBody,
        image: MultipartBody.Part,
        editProfileHandler: EditProfileHandler
    ) {
        apiCreate()
        api.uploadProfileImageAPI(id, image).enqueue(object : Callback<UploadProfileIMageExample> {
            override fun onResponse(
                call: Call<UploadProfileIMageExample>,
                response: Response<UploadProfileIMageExample>
            ) {
                if (response.code() == 200) {
                    editProfileHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            editProfileHandler.onError(message)
                        } else {
                            editProfileHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<UploadProfileIMageExample>, t: Throwable) {
                editProfileHandler.onError(t.message)
            }
        })
    }

    //getUserProfile
    fun getCurrentUser(token: String, id: String, getCurrentUserHandler: GetCurrentUserHandler) {
        apiCreate()
        api.getCurrentUserId(token, id).enqueue(object : Callback<ProfileExample> {
            override fun onResponse(
                call: Call<ProfileExample>,
                response: Response<ProfileExample>
            ) {
                if (response.code() == 200) {
                    getCurrentUserHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getCurrentUserHandler.onError(message)
                        } else {
                            getCurrentUserHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ProfileExample>, t: Throwable) {
                getCurrentUserHandler.onError(t.message)
            }
        })
    }

    //uploadPdfFile
    fun uploadPdfFileMethod(
        file: MultipartBody.Part,
        id: String,
        uploadPdfHandler: UploadPdfHandler
    ) {
        apiCreate()
        api.uploadPdfFileAPI(file, id).enqueue(object : Callback<UploadPdfExample> {
            override fun onResponse(
                call: Call<UploadPdfExample>,
                response: Response<UploadPdfExample>
            ) {
                if (response.code() == 200) {
                    uploadPdfHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            uploadPdfHandler.onError(message)
                        } else {
                            uploadPdfHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<UploadPdfExample>, t: Throwable) {
                uploadPdfHandler.onError(t.message)
            }
        })
    }

    //chnagepasswordMethod
    fun changePasswordMethod(
        token: String?,
        newPassword: String,
        changePasswordHandler: ChangePasswordHandler
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("newPassword", newPassword)
        apiCreate()
        api.changePasswordAPI(token!!, jsonObject)
            .enqueue(object : Callback<ChangePasswordExample> {
                override fun onResponse(
                    call: Call<ChangePasswordExample>,
                    response: Response<ChangePasswordExample>
                ) {
                    if (response.code() == 200) {
                        changePasswordHandler.onSuccess(response.body())
                    } else {

                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                changePasswordHandler.onError(message)
                            } else {
                                changePasswordHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<ChangePasswordExample>, t: Throwable) {
                    changePasswordHandler.onError(t.message)
                }
            })
    }

    fun changeResetPasswordMethod(
        token: String?,
        id: String,
        oldPassword: String,
        newPassword: String,
        changeResetPasswordHandler: ChangeResetPasswordHandler
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("oldPassword", oldPassword)
        jsonObject.addProperty("newPassword", newPassword)
        apiCreate()
        api.changeResetPasswordAPI(token!!, id, jsonObject)
            .enqueue(object : Callback<ResetChangePasswordExample> {
                override fun onResponse(
                    call: Call<ResetChangePasswordExample>,
                    response: Response<ResetChangePasswordExample>
                ) {
                    if (response.code() == 200) {
                        changeResetPasswordHandler.onSuccess(response.body())
                    } else {

                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                changeResetPasswordHandler.onError(message)
                            } else {
                                changeResetPasswordHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<ResetChangePasswordExample>, t: Throwable) {
                    changeResetPasswordHandler.onError(t.message)
                }
            })
    }

    fun CreateJobMethod(
        title: String?,
        categoryId: String?,
        userId: String?,
        pricefrom: String,
        priceto: String,
        location: String,
        workers: String,
        jobType: String,
        firstName: String,
        lastName: String,
        email: String,
        country: String,
        scopeOfWork: String,
        planOfAction: String,
        constructionDocumentation: String,
        companymail: String,
        companyfullnumber: String,
        companyAddress: String,
        experience: String,
        skills: String,
        createjobHandler: CreatejobHandler
    ) {

//        var jsonArraycompany: JsonArray = Gson().toJsonTree(company).asJsonArray
        val jsonObject = JsonObject()
        val jsonObject11 = JsonObject()
        jsonObject.addProperty("title", title)
        jsonObject.addProperty("categoryId", categoryId)
        jsonObject.addProperty("userId", userId)
        jsonObject.addProperty("priceFrom", pricefrom)
        jsonObject.addProperty("priceTo", priceto)
        jsonObject.addProperty("location", location)
        jsonObject.addProperty("workers", workers)
        jsonObject.addProperty("jobType", jobType)
        jsonObject.addProperty("firstName", firstName)
        jsonObject.addProperty("lastName", lastName)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("country", country)
        jsonObject.addProperty("scopeOfWork", scopeOfWork)
        jsonObject.addProperty("planOfAction", planOfAction)
        jsonObject.addProperty("constructionDocumentation", constructionDocumentation)
        jsonObject.add("company", jsonObject11)
        jsonObject11.addProperty("emailId", companymail)
        jsonObject11.addProperty("phoneNumber", companyfullnumber)
        jsonObject11.addProperty("address", companyAddress)
        jsonObject11.addProperty("experience", experience)
        jsonObject11.addProperty("skills", skills)
        apiCreate()
        api.createjobAPI(jsonObject).enqueue(object : Callback<CreateobsExamples> {
            override fun onResponse(
                call: Call<CreateobsExamples>, response: Response<CreateobsExamples>
            ) {
                if (response.code() == 200) {
                    createjobHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            createjobHandler.onError(message)
                        } else {
                            createjobHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<CreateobsExamples>, t: Throwable) {
                createjobHandler.onError(t.message)
            }
        })
    }

    //getalljobsapi
    fun getalljobsAPI(getalljobsHandler: GetalljobsHandler) {
        apiCreate()
        api.getAllJobs().enqueue(object : Callback<GetJobsExample> {
            override fun onResponse(
                call: Call<GetJobsExample>, response: Response<GetJobsExample>
            ) {
                if (response.code() == 200) {
                    getalljobsHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getalljobsHandler.onError(message)
                        } else {
                            getalljobsHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<GetJobsExample>, t: Throwable) {
                getalljobsHandler.onError(t.message)
            }
        })
    }

    //getrecentpostAPI
    fun getrecentpostMethod(recentPostHandler: RecentPostHandler) {
        apiCreate()
        api.recentPostAPI().enqueue(object : Callback<RecentPostsExample> {
            override fun onResponse(
                call: Call<RecentPostsExample>, response: Response<RecentPostsExample>
            ) {
                if (response.code() == 200) {
                    recentPostHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            recentPostHandler.onError(message)
                        } else {
                            recentPostHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<RecentPostsExample>, t: Throwable) {
                recentPostHandler.onError(t.message)
            }
        })
    }

    //getpopularjobs
    fun getpopularjobsMethod(popularjobsHandler: PopularjobsHandler) {
        apiCreate()
        api.getPopularjobs().enqueue(object : Callback<PopularJobsExample> {
            override fun onResponse(
                call: Call<PopularJobsExample>, response: Response<PopularJobsExample>
            ) {
                if (response.code() == 200) {
                    popularjobsHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            popularjobsHandler.onError(message)
                        } else {
                            popularjobsHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<PopularJobsExample>, t: Throwable) {
                popularjobsHandler.onError(t.message)
            }
        })
    }

    //getjobbyid
    fun getJobById(id: String, getJobByIdHandler: GetJobByIdHandler) {
        apiCreate()
        api.getjobbyId(id).enqueue(object : Callback<GetJobByIdExample> {
            override fun onResponse(
                call: Call<GetJobByIdExample>,
                response: Response<GetJobByIdExample>
            ) {
                if (response.code() == 200) {
                    getJobByIdHandler.onSuccess(response.body())
                } else {

                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getJobByIdHandler.onError(message)
                        } else {
                            getJobByIdHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<GetJobByIdExample>, t: Throwable) {
                getJobByIdHandler.onError(t.message)
            }

        })

    }

    //updateusebyId
    fun updateuserById(
        token: String?, id: String, userName: String?, companyName: String?, phoneNumber: String,
        email: String, updateuserByidHandler: UpdateuserByidHandler
    ) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("userName", userName)
        jsonObject.addProperty("companyName", companyName)
        jsonObject.addProperty("phoneNumber", phoneNumber)
        jsonObject.addProperty("email", email)
        apiCreate()
        api.updateuserbyIdAPI(token!!, id, jsonObject)
            .enqueue(object : Callback<UpdateUserExample> {
                override fun onResponse(
                    call: Call<UpdateUserExample>,
                    response: Response<UpdateUserExample>
                ) {
                    if (response.code() == 200) {
                        updateuserByidHandler.onSuccess(response.body())
                    } else {
                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                updateuserByidHandler.onError(message)
                            } else {
                                updateuserByidHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<UpdateUserExample>, t: Throwable) {
                    updateuserByidHandler.onError(t.message)
                }
            })
    }

    //createcategoryHandler
    fun createCategoryMethod(category: String?, createCategoryHandler: CreateCategoryHandler) {
        apiCreate()
        val jsonObject = JsonObject()
        jsonObject.addProperty("category", category)
        api.createCategoryAPI(jsonObject).enqueue(object : Callback<CreateCategoryExample> {
            override fun onResponse(
                call: Call<CreateCategoryExample>,
                response: Response<CreateCategoryExample>
            ) {
                if (response.code() == 200) {
                    createCategoryHandler.onSuccess(response.body())
                } else {

                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            createCategoryHandler.onError(message)
                        } else {
                            createCategoryHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<CreateCategoryExample>, t: Throwable) {
                createCategoryHandler.onError(t.message)
            }

        })
    }

    //applyjobs
    fun applyJobsMethod(
        userId: String?,
        jobId: String?,
        firstName: String?,
        lastName: String?,
        email: String?,
        country: String?,
        describe: String?,
        resume: String?,
        status: String?,
        applyJobsHandler: ApplyJobsHandler
    ) {
        apiCreate()
        val jsonObject = JsonObject()
        jsonObject.addProperty("userId", userId)
        jsonObject.addProperty("jobId", jobId)
        jsonObject.addProperty("firstName", firstName)
        jsonObject.addProperty("lastName", lastName)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("country", country)
        jsonObject.addProperty("describe", describe)
        jsonObject.addProperty("resume", resume)
        jsonObject.addProperty("status", status)
        api.applyjobs(jsonObject).enqueue(object : Callback<ApplyjobExample> {
            override fun onResponse(
                call: Call<ApplyjobExample>,
                response: Response<ApplyjobExample>
            ) {
                if (response.code() == 200) {
                    applyJobsHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            applyJobsHandler.onError(message)
                        } else {
                            applyJobsHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<ApplyjobExample>, t: Throwable) {
                applyJobsHandler.onError(t.message)
            }
        })
    }

    //getallcategories
    fun getallcategories(getallCategoryHandler: GetallCategoryHandler) {
        apiCreate()
        api.getallcategory().enqueue(object : Callback<GetallCategoryExample> {
            override fun onResponse(
                call: Call<GetallCategoryExample>,
                response: Response<GetallCategoryExample>
            ) {
                if (response.code() == 200) {
                    getallCategoryHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getallCategoryHandler.onError(message)
                        } else {
                            getallCategoryHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetallCategoryExample>, t: Throwable) {
                getallCategoryHandler.onError(t.message)
            }

        })
    }

    //getallloc
    fun getallocations(getallLocationHandler: GetallLocationHandler) {
        apiCreate()
        api.getAlllocationAPI().enqueue(object : Callback<GetallLocationExample> {
            override fun onResponse(
                call: Call<GetallLocationExample>,
                response: Response<GetallLocationExample>
            ) {
                if (response.code() == 200) {
                    getallLocationHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getallLocationHandler.onError(message)
                        } else {
                            getallLocationHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetallLocationExample>, t: Throwable) {
                getallLocationHandler.onError(t.message)
            }
        })
    }

    //addtofavApis
    fun addtoFavMethod(
        userId: String?,
        jobId: String?,
        status: String?,
        addtoFavHandler: AddtoFavHandler
    ) {
        apiCreate()
        val jsonObject = JsonObject()
        jsonObject.addProperty("userId", userId)
        jsonObject.addProperty("jobId", jobId)
        jsonObject.addProperty("status", status)
        api.addtofavAPI(jsonObject).enqueue(object : Callback<AddToFavExample> {
            override fun onResponse(
                call: Call<AddToFavExample>,
                response: Response<AddToFavExample>
            ) {
                if (response.code() == 200) {
                    addtoFavHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            addtoFavHandler.onError(message)
                        } else {
                            addtoFavHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<AddToFavExample>, t: Throwable) {
                addtoFavHandler.onError(t.message)
            }
        })
    }

    //getappliedjobsbyid
    fun getappliedjobsbyId(id: String, getappliedJobsByidHandler: GetappliedJobsByidHandler) {
        apiCreate()
        api.getapplyjobbyid(id).enqueue(object : Callback<GetapplyJobByidExample> {
            override fun onResponse(
                call: Call<GetapplyJobByidExample>,
                response: Response<GetapplyJobByidExample>
            ) {
                if (response.code() == 200) {
                    getappliedJobsByidHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getappliedJobsByidHandler.onError(message)
                        } else {
                            getappliedJobsByidHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<GetapplyJobByidExample>, t: Throwable) {
                getappliedJobsByidHandler.onError(t.message)
            }
        })
    }

    //getfavJobsById___________________________________________________________________________________________________
    fun getfavjobsbyid(id: String, getFavJobbyidsHandler: GetFavJobbyidsHandler) {
        apiCreate()
        api.getfavjobsByIdAPI(id).enqueue(object : Callback<GetFavjobsExamples> {
            override fun onResponse(
                call: Call<GetFavjobsExamples>,
                response: Response<GetFavjobsExamples>
            ) {
                if (response.code() == 200) {
                    getFavJobbyidsHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getFavJobbyidsHandler.onError(message)
                        } else {
                            getFavJobbyidsHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<GetFavjobsExamples>, t: Throwable) {
                getFavJobbyidsHandler.onError(t.message)
            }
        })
    }

    fun applyfilters(
        categoryId: String, jobType: String, location: String,
        priceFrom: String, priceTo: String, applyFilterHandler: ApplyFilterHandler
    ) {
        apiCreate()
        api.applyFilterAPI(categoryId, jobType, location, priceFrom, priceTo)
            .enqueue(object : Callback<JobFilterExamples> {
                override fun onResponse(
                    call: Call<JobFilterExamples>,
                    response: Response<JobFilterExamples>
                ) {
                    if (response.code() == 200) {
                        applyFilterHandler.onSuccess(response.body())
                    } else {
                        val responceData = SocketConnection.convertStreamToString(
                            response.errorBody()!!.byteStream()
                        )
                        try {
                            val jsonObject = JSONObject(responceData)
                            val message = jsonObject.optString("message")
                            val error = jsonObject.optString("error")
                            if (!message.equals("", ignoreCase = true)) {
                                applyFilterHandler.onError(message)
                            } else {
                                applyFilterHandler.onError(error)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }


                    }
                }

                override fun onFailure(call: Call<JobFilterExamples>, t: Throwable) {
                    applyFilterHandler.onError(t.message)
                }

            })
    }

    //searchapi
    fun searchPI(title: String, searchHandler: SearchHandler) {
        apiCreate()
        api.searchAPI(title).enqueue(object : Callback<SearchExamples> {
            override fun onResponse(
                call: Call<SearchExamples>,
                response: Response<SearchExamples>
            ) {
                if (response.code() == 200) {
                    searchHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            searchHandler.onError(message)
                        } else {
                            searchHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }


                }
            }

            override fun onFailure(call: Call<SearchExamples>, t: Throwable) {
                searchHandler.onError(t.message)
            }

        })
    }

    fun getaplliedjobbyuserid(
        id: String,
        getAppliedjobsbyUseridHandler: GetAppliedjobsbyUseridHandler
    ) {
        apiCreate()
        api.getappliedjobbyid(id).enqueue(object : Callback<GetApplyJobsByIdExamples> {
            override fun onResponse(
                call: Call<GetApplyJobsByIdExamples>,
                response: Response<GetApplyJobsByIdExamples>
            ) {
                if (response.code() == 200) {
                    getAppliedjobsbyUseridHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getAppliedjobsbyUseridHandler.onError(message)
                        } else {
                            getAppliedjobsbyUseridHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetApplyJobsByIdExamples>, t: Throwable) {
                getAppliedjobsbyUseridHandler.onError(t.message)
            }

        })
    }

    //getemployerCreatedJob_______________________________________________________________________________________________________________
    fun getemployercreatedjob(
        id: String,
        getEmployerCreatedJobHandler: GetEmployerCreatedJobHandler
    ) {
        apiCreate()
        api.getemployercreateJob(id).enqueue(object : Callback<GetEmployerCreatedJob> {
            override fun onResponse(
                call: Call<GetEmployerCreatedJob>,
                response: Response<GetEmployerCreatedJob>
            ) {
                if (response.code() == 200) {
                    getEmployerCreatedJobHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getEmployerCreatedJobHandler.onError(message)
                        } else {
                            getEmployerCreatedJobHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetEmployerCreatedJob>, t: Throwable) {
                getEmployerCreatedJobHandler.onError(t.message)
            }

        })
    }

    //getjobApplicants________________________________________________________________________________________________________________________________________________________________
    fun getjobApplicants(
        jobid: String,
        getJobApplicantsHandler: GetJobApplicantsHandler
    ) {
        apiCreate()
        api.getjobapplicants(jobid).enqueue(object : Callback<GetJobApplicants> {
            override fun onResponse(
                call: Call<GetJobApplicants>,
                response: Response<GetJobApplicants>
            ) {
                if (response.code() == 200) {
                    getJobApplicantsHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getJobApplicantsHandler.onError(message)
                        } else {
                            getJobApplicantsHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetJobApplicants>, t: Throwable) {
                getJobApplicantsHandler.onError(t.message)
            }

        })
    }

    //getapplieduserdetail____________________________________________________________________________________________________________________
    fun getAppliedUserDetail(
        userId: String,
        applyId: String,
        getAppliedUserDetailHandler: GetAppliedUserDetailHandler
    ) {
        apiCreate()
        api.getapplieduserDetail(userId, applyId).enqueue(object : Callback<GetAppliedUserDetail> {
            override fun onResponse(
                call: Call<GetAppliedUserDetail>,
                response: Response<GetAppliedUserDetail>
            ) {
                if (response.code() == 200) {
                    getAppliedUserDetailHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getAppliedUserDetailHandler.onError(message)
                        } else {
                            getAppliedUserDetailHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetAppliedUserDetail>, t: Throwable) {
                getAppliedUserDetailHandler.onError(t.message)
            }

        })

    }

    //removejobsfromwishlist
    fun removejobsfromWishlist(
        jobid: String,
        removeJobsFromWishlistHandler: RemoveJobsFromWishlistHandler
    ) {
        apiCreate()
        api.removeWishListJobs(jobid).enqueue(object : Callback<RemoveJobsFromFav> {
            override fun onResponse(
                call: Call<RemoveJobsFromFav>,
                response: Response<RemoveJobsFromFav>
            ) {
                if (response.code() == 200) {
                    removeJobsFromWishlistHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            removeJobsFromWishlistHandler.onError(message)
                        } else {
                            removeJobsFromWishlistHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<RemoveJobsFromFav>, t: Throwable) {
                removeJobsFromWishlistHandler.onError(t.message)
            }
        })
    }
    //googlesociallogin

    fun socialLogin(
        socialLinkId: String,
        platform: String,
        email: String,
        userName: String,
        phoneNumber: String,
        socialLoginHandler: SocialLoginHandler
    ) {
        apiCreate()
        val jsonObject = JsonObject()
        jsonObject.addProperty("socialLinkId", socialLinkId)
        jsonObject.addProperty("platform", platform)
        jsonObject.addProperty("email", email)
        jsonObject.addProperty("userName", userName)
        jsonObject.addProperty("phoneNumber", phoneNumber)
        api.sociallogin(jsonObject).enqueue(object : Callback<GoogleSignInModel> {
            override fun onResponse(
                call: Call<GoogleSignInModel>,
                response: Response<GoogleSignInModel>
            ) {
                var acesstoken: String? = null
                acesstoken = response.headers()["x-access-token"]
                if (response.code() == 200) {
                    socialLoginHandler.onSuccess(response.body(), acesstoken)

                } else {

                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            socialLoginHandler.onError(message)
                        } else {
                            socialLoginHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GoogleSignInModel>, t: Throwable) {
                socialLoginHandler.onError(t.message)
            }

        })
    }


    //getoldChats________________________________________________________________________________________________________________________
    fun getOldChats(
        room_id: String,
        pageNo: Int,
        pageSize: Int,
        getOldChatsHandler: GetOldChatsHandler
    ) {
        apiCreate()
        api.getOldChats(room_id, pageNo, pageSize).enqueue(object : Callback<GetOldChats> {
            override fun onResponse(call: Call<GetOldChats>, response: Response<GetOldChats>) {

                if (response.code() == 200) {
                    getOldChatsHandler.onSuccess(response.body())
                } else {

                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getOldChatsHandler.onError(message)
                        } else {
                            getOldChatsHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

            }

            override fun onFailure(call: Call<GetOldChats>, t: Throwable) {
                getOldChatsHandler.onError(t.message)
            }


        })
    }

    //getallusers___________________________________________________________________________________________________________________________________________
    fun getallusers(token: String?,
        getAllUsersHandler: GetAllUsersHandler

    ) {
        apiCreate()
        api.getallUsers(token!!).enqueue(object : Callback<GetAllUser> {
            override fun onResponse(call: Call<GetAllUser>, response: Response<GetAllUser>) {
                if (response.code() == 200) {
                    getAllUsersHandler.onSuccess(response.body())
                } else {
                    val responceData = SocketConnection.convertStreamToString(
                        response.errorBody()!!.byteStream()
                    )
                    try {
                        val jsonObject = JSONObject(responceData)
                        val message = jsonObject.optString("message")
                        val error = jsonObject.optString("error")
                        if (!message.equals("", ignoreCase = true)) {
                            getAllUsersHandler.onError(message)
                        } else {
                            getAllUsersHandler.onError(error)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetAllUser>, t: Throwable) {
                getAllUsersHandler.onError(t.message)

            }


        })
    }


}




