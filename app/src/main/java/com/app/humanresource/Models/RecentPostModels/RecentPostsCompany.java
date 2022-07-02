
package com.app.humanresource.Models.RecentPostModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  RecentPostsCompany {

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

    public RecentPostsCompany withEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public RecentPostsCompany withExperience(String experience) {
        this.experience = experience;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RecentPostsCompany withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RecentPostsCompany withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public RecentPostsCompany withSkills(String skills) {
        this.skills = skills;
        return this;
    }

}
