package com.hxp.happyschool.beans;

/**
 * wifi实体类
 * Created by hxp on 16-1-25.
 */
public class WifiBean {


    public String mac;
    public String address;
    public String ssid;
    public int distance;
    public int mdb;


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


    public int getMdb() {
        return mdb;
    }


    public void setMdb(int mdb) {
        this.mdb = mdb;
    }


    public int getDistance() {
        return distance;
    }


    public void setDistance(int distance) {
        this.distance = distance;
    }
}
