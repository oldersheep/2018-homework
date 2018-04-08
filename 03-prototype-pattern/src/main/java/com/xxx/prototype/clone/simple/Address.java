package com.xxx.prototype.clone.simple;

public class Address implements Cloneable{

    private String province;
    private String city;
    private String county;

    public Address() {
    }

    public Address(String province, String city, String county) {
        this.province = province;
        this.city = city;
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
