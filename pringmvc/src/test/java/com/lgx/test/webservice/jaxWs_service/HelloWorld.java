package com.lgx.test.webservice.jaxWs_service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * 定义SEI(WebService EndPoint Interface(终端))
 */
//使用@WebService注解标注WebServiceI接口
@WebService()
//@SOAPBinding(style = SOAPBinding.Style.RPC)
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface  HelloWorld {
    //使用@WebMethod注解标注WebServiceI接口中的方法
    @WebMethod(operationName = "getHelloWorldAsString")
    String getHelloWorldAsString(String name);


    String udpMethod(@WebParam(name="user") Person user);

    List<Address> getAddressList();

    Person getPerson();
}
