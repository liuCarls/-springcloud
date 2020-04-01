package com.lgx.test.webservice.jaxWs_client;

import javax.jws.WebService;

//@WebService(endpointInterface = "com.lgx.test.webservice.jaxWs_service.HelloWorld")
//报”类上不存在 Web 服务注释“
//要保证接口的targetNamespace与发布的targetNamespace一致,找不到XXX的分派方法
@WebService(targetNamespace = "http://wstest.lgx.com")
public interface HelloWorld {

    String getHelloWorldAsString(String name);
}
