package com.lgx.test.webservice.jaxWs_service;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7788/ws/hello", new HelloWorldImpl());
    }
}
