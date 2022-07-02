
package com.app.humanresource.Models.GetPopularjobsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class  PopularJobsCompany {

    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("skills")
    @Expose
    private String skills;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
