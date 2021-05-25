
package com.example.newtaskcmss.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("nameLocale")
    @Expose
    private String nameLocale;
    @SerializedName("name")
    @Expose
    private String name;

    public String getNameLocale() {
        return nameLocale;
    }

    public void setNameLocale(String nameLocale) {
        this.nameLocale = nameLocale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
