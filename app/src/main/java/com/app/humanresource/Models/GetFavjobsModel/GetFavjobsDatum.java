
package com.app.humanresource.Models.GetFavjobsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetFavjobsDatum {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("jobId")
    @Expose
    private  GetFavjobsJobId jobId;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public  GetFavjobsJobId getJobId() {
        return jobId;
    }

    public void setJobId( GetFavjobsJobId jobId) {
        this.jobId = jobId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
