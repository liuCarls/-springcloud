package com.lgx.test.webservice.jaxWs_service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

////使用@WebService注解标注WebServiceI接口的实现类WebServiceImpl
//portName-------wsdl:port的name属性。缺省值为 WebService.name+Port。
//name-----wsdl:portType的name属性。缺省值为 Java 类或接口的非限定名称。
//serviceName-------wsdl:service的name属性。缺省值为 Java 类的简单名称 + Service。
//targetNamespace----指定从 Web Service 生成的 WSDL 和 XML 元素的 XML 名称空间。缺省值为从包含该 Web Service 的包名映射的名称空间。
//endpointInterface----指定用于定义服务的抽象 Web Service 约定的服务端点接口的限定名。如果指定了此限定名，那么会使用该服务端点接口来确定抽象 WSDL 约定。
//wsdlLocation------指定用于定义 Web Service的WSDL文档的Web地址。Web地址可以是相对路径或绝对路径。
//@WebService()
@WebService(targetNamespace = "http://wstest.lgx.com", name = "HelloWorld12")
public class HelloWorldImpl implements HelloWorld {

    @Override
    @WebMethod(operationName = "getHelloWorldAsString")
    public String getHelloWorldAsString(String name) {
        System.out.println(123456);
        return "Hello World JAX-WS " + name;
    }

    @Override
    @WebMethod(operationName = "udpMethod")
    public String udpMethod(Person user) {
        return user.getName()+" login, he/she salary is "+user.getSalary();
    }

    @Override
    public List<Address> getAddressList() {

        return null;
    }

    @Override
    public Person getPerson() {
        return null;
    }
}
