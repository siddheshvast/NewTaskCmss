
package com.example.newtaskcmss.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Classification {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("names")
    @Expose
    private List<Name> names = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

}
