
package com.example.newtaskcmss.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class EntryPoint {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("position")
    @Expose
    private Position__1 position;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Position__1 getPosition() {
        return position;
    }

    public void setPosition(Position__1 position) {
        this.position = position;
    }

}
