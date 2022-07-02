
package com.app.humanresource.Models.GetAllLocationModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetallLocationDatum {

    @SerializedName("_id")
    @Expose
    private Object id;
    @SerializedName("location")
    @Expose
    private List<String> location = null;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

}
