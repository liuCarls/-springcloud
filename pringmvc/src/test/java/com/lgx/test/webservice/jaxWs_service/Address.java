package com.lgx.test.webservice.jaxWs_service;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private String city;
    private int port;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
