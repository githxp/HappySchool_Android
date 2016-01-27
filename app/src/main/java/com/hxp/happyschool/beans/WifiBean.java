package com.hxp.happyschool.beans;

/**
 * wifi实体类
 * Created by hxp on 16-1-25.
 */
public class WifiBean {


    public String mac;
    public String address;
    public String ssid;
    public String distance;


    /*//创建构造方法
    public WifiBean(String address, String mac, String ssid) {
        this.address = address;
        this.mac = mac;
        this.ssid = ssid;
    }*/


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getMac() {
        return mac;
    }


    public void setMac(String mac) {
        this.mac = mac;
    }


    public String getSsid() {
        return ssid;
    }


    public void setSsid(String ssid) {
        this.ssid = ssid;
    }


    public String getDistance() {
        return distance;
    }


    public void setDistance(String distance) {
        this.distance = distance;
    }
}
