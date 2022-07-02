
package com.app.humanresource.Models.RecentPostModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class  RecentPostsMessage {

    @SerializedName("company")
    @Expose
    private RecentPostsCompany company;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("priceFrom")
    @Expose
    private Integer priceFrom;
    @SerializedName("priceTo")
    @Expose
    private Integer priceTo;
    @SerializedName("workers")
    @Expose
    private Integer workers;
    @SerializedName("jobType")
    @Expose
    private String jobType;
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
    @SerializedName("scopeOfWork")
    @Expose
    private String scopeOfWork;
    @SerializedName("planOfAction")
    @Expose
    private String planOfAction;
    @SerializedName("constructionDocumentation")
    @Expose
    private String constructionDocumentation;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("category")
    @Expose
    private RecentPostsCategory category;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("updatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public RecentPostsCompany getCompany() {
        return company;
    }

    public void setCompany(RecentPostsCompany company) {
        this.company = company;
    }

    public RecentPostsMessage withCompany(RecentPostsCompany company) {
        this.company = company;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RecentPostsMessage withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RecentPostsMessage withLocation(String location) {
        this.location = location;
        return this;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public RecentPostsMessage withPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
        return this;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public RecentPostsMessage withPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
        return this;
    }

    public Integer getWorkers() {
        return workers;
    }

    public void setWorkers(Integer workers) {
        this.workers = workers;
    }

    public RecentPostsMessage withWorkers(Integer workers) {
        this.workers = workers;
        return this;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public RecentPostsMessage withJobType(String jobType) {
        this.jobType = jobType;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public RecentPostsMessage withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RecentPostsMessage withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RecentPostsMessage withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RecentPostsMessage withCountry(String country) {
        this.country = country;
        return this;
    }

    public String getScopeOfWork() {
        return scopeOfWork;
    }

    public void setScopeOfWork(String scopeOfWork) {
        this.scopeOfWork = scopeOfWork;
    }

    public RecentPostsMessage withScopeOfWork(String scopeOfWork) {
        this.scopeOfWork = scopeOfWork;
        return this;
    }

    public String getPlanOfAction() {
        return planOfAction;
    }

    public void setPlanOfAction(String planOfAction) {
        this.planOfAction = planOfAction;
    }

    public RecentPostsMessage withPlanOfAction(String planOfAction) {
        this.planOfAction = planOfAction;
        return this;
    }

    public String getConstructionDocumentation() {
        return constructionDocumentation;
    }

    public void setConstructionDocumentation(String constructionDocumentation) {
        this.constructionDocumentation = constructionDocumentation;
    }

    public RecentPostsMessage withConstructionDocumentation(String constructionDocumentation) {
        this.constructionDocumentation = constructionDocumentation;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RecentPostsMessage withId(String id) {
        this.id = id;
        return this;
    }

    public RecentPostsCategory getCategory() {
        return category;
    }

    public void setCategory(RecentPostsCategory category) {
        this.category = category;
    }

    public RecentPostsMessage withCategory(RecentPostsCategory category) {
        this.category = category;
        return this;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public RecentPostsMessage withCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public RecentPostsMessage withUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public RecentPostsMessage withV(Integer v) {
        this.v = v;
        return this;
    }

}
