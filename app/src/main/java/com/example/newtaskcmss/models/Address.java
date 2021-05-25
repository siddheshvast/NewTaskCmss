
package com.example.newtaskcmss.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Address {

    @SerializedName("municipalitySubdivision")
    @Expose
    private String municipalitySubdivision;
    @SerializedName("municipality")
    @Expose
    private String municipality;
    @SerializedName("countrySubdivision")
    @Expose
    private String countrySubdivision;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("countryCodeISO3")
    @Expose
    private String countryCodeISO3;
    @SerializedName("freeformAddress")
    @Expose
    private String freeformAddress;
    @SerializedName("localName")
    @Expose
    private String localName;
    @SerializedName("streetName")
    @Expose
    private String streetName;
    @SerializedName("streetNumber")
    @Expose
    private String streetNumber;

    public String getMunicipalitySubdivision() {
        return municipalitySubdivision;
    }

    public void setMunicipalitySubdivision(String municipalitySubdivision) {
        this.municipalitySubdivision = municipalitySubdivision;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCountrySubdivision() {
        return countrySubdivision;
    }

    public void setCountrySubdivision(String countrySubdivision) {
        this.countrySubdivision = countrySubdivision;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCodeISO3() {
        return countryCodeISO3;
    }

    public void setCountryCodeISO3(String countryCodeISO3) {
        this.countryCodeISO3 = countryCodeISO3;
    }

    public String getFreeformAddress() {
        return freeformAddress;
    }

    public void setFreeformAddress(String freeformAddress) {
        this.freeformAddress = freeformAddress;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

}
