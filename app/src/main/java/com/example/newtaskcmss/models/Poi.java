
package com.example.newtaskcmss.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Poi {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("categorySet")
    @Expose
    private List<CategorySet> categorySet = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;
    @SerializedName("classifications")
    @Expose
    private List<Classification> classifications = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CategorySet> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(List<CategorySet> categorySet) {
        this.categorySet = categorySet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

}
