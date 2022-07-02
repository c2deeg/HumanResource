
package com.app.humanresource.Models.RecentPostModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class  RecentPostsCategory {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("updatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RecentPostsCategory withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RecentPostsCategory withId(String id) {
        this.id = id;
        return this;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public RecentPostsCategory withCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public RecentPostsCategory withUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public RecentPostsCategory withV(Integer v) {
        this.v = v;
        return this;
    }

}
