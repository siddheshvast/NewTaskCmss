
package com.example.newtaskcmss.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewport {

    @SerializedName("topLeftPoint")
    @Expose
    private TopLeftPoint topLeftPoint;
    @SerializedName("btmRightPoint")
    @Expose
    private BtmRightPoint btmRightPoint;

    public TopLeftPoint getTopLeftPoint() {
        return topLeftPoint;
    }

    public void setTopLeftPoint(TopLeftPoint topLeftPoint) {
        this.topLeftPoint = topLeftPoint;
    }

    public BtmRightPoint getBtmRightPoint() {
        return btmRightPoint;
    }

    public void setBtmRightPoint(BtmRightPoint btmRightPoint) {
        this.btmRightPoint = btmRightPoint;
    }
//arre vdo dekh n ormsqlite ka :| ye barabr nhi hai kya pata nh re vo video kidar hai ab ye isme db ka kch alag hi lg rh h
}
