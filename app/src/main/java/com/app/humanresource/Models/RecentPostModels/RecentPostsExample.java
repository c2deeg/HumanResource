
package com.app.humanresource.Models.RecentPostModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentPostsExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private List<RecentPostsMessage> message = null;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public RecentPostsExample withIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public RecentPostsExample withStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public List<RecentPostsMessage> getMessage() {
        return message;
    }

    public void setMessage(List<RecentPostsMessage> message) {
        this.message = message;
    }

    public RecentPostsExample withMessage(List<RecentPostsMessage> message) {
        this.message = message;
        return this;
    }

}
