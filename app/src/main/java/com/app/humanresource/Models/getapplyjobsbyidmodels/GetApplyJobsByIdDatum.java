
package com.app.humanresource.Models.getapplyjobsbyidmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetApplyJobsByIdDatum {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("describe")
    @Expose
    private String describe;
    @SerializedName("resume")
    @Expose
    private String resume;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("applyBy")
    @Expose
    private GetApplyJobsByIdApplyBy applyBy;
    @SerializedName("job")
    @Expose
    private GetApplyJobsByIdJob job;
    @SerializedName("postedBy")
    @Expose
    private String postedBy;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GetApplyJobsByIdApplyBy getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(GetApplyJobsByIdApplyBy applyBy) {
        this.applyBy = applyBy;
    }

    public GetApplyJobsByIdJob getJob() {
        return job;
    }

    public void setJob(GetApplyJobsByIdJob job) {
        this.job = job;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
