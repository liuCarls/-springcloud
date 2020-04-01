package com.lgx.test.webservice.jaxWs_client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:7788/ws/hello?wsdl");

        //QName的第一个参数和第二个参数在wsdl的definitions的targetNamespace,name属性
        QName qname = new QName("http://wstest.lgx.com", "HelloWorldImplService");
        Service service = Service.create(url, qname);
        QName qname2 = new QName("http://wstest.lgx.com", "HelloWorld12Port");
//        service.getPort()

        HelloWorld hello = service.getPort(qname2,HelloWorld.class); //如果不用QName，报”未定义的端口类型“
        //

        System.out.println(hello.getHelloWorldAsString("XXX rpc"));
    }
}
