
package com.example.newtaskcmss.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataSources {

    @SerializedName("poiDetails")
    @Expose
    private List<PoiDetail> poiDetails = null;

    public List<PoiDetail> getPoiDetails() {
        return poiDetails;
    }

    public void setPoiDetails(List<PoiDetail> poiDetails) {
        this.poiDetails = poiDetails;
    }

}
