package com.lgx.test.webservice;

//import org.apache.axis.jaxWs_client.Service;
//import javax.xml.ws.Endpoint;

//import java.net.MalformedURLException;
//import java.net.URL;
//import org.codehaus.xfire.jaxWs_client.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 使用Axis或XFire实现WebService:
 *
 * Axis2是Apache下的一个重量级WebService框架，准确说它是一个Web Services / SOAP / WSDL 的引擎，是WebService框架的集大成者，它能不但能制作和发布WebService，而且可以生成Java和其他语言版WebService客户端和服务端代码。这是它的优势所在。但是，这也不可避免的导致了Axis2的复杂性，使用过的开发者都知道，它所依赖的包数量和大小都是很惊人的，打包部署发布都比较麻烦，不能很好的与现有应用整合为一体。但是如果你要开发Java之外别的语言客户端，Axis2提供的丰富工具将是你不二的选择。
 *
 * XFire是一个高性能的WebService框架，在Java6之前，它的知名度甚至超过了Apache的Axis2，XFire的优点是开发方便，与现有的Web整合很好，可以融为一体，并且开发也很方便。但是对Java之外的语言，没有提供相关的代码工具。XFire后来被Apache收购了，原因是它太优秀了，收购后，随着Java6 JWS的兴起，开源的WebService引擎已经不再被看好，渐渐的都败落了
 */
public class TestClient {
    public static void main(String[] args) {
//通过AXIS调用
//        try {
//            String endpoint = "http://localhost:8080/ca3/services/caSynrochnized?wsdl";
//            //直接引用远程的wsdl文件
//            //以下都是套路
//            Service service = new Service();
//            Call call = (Call) service.createCall();
//            call.setTargetEndpointAddress(endpoint);
//            call.setOperationName("addUser");//WSDL里面描述的接口名称
//            call.addParameter("userName", org.apache.axis.encoding.XMLType.XSD_DATE,
//                    javax.xml.rpc.ParameterMode.IN);//接口的参数
//            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型
//            String temp = "测试人员";
//            String result = (String)call.invoke(new Object[]{temp});
//            //给方法传递参数，并且调用方法
//            System.out.println("result is "+result);
//        }
//        catch (Exception e) {
//            System.err.println(e.toString());
//        }

        //通过XFire调用
//        Client jaxWs_client = new Client(new URL("http://127.0.0.1:8080/XFire_demo/services/XFireServer?wsdl"));


        //通过CXF调用


        try {
            URL url = new URL("http://127.0.0.1:8888/ws/phoneService?WSDL");
            URLConnection conn = url.openConnection();
            HttpURLConnection con = (HttpURLConnection) conn;
            // 4，设置请求方式和请求头：
            con.setDoInput(true); // 是否有入参
            con.setDoOutput(true); // 是否有出参
            con.setRequestMethod("POST"); // 设置请求方式
            con.setRequestProperty("content-type", "text/xml;charset=UTF-8");

            String requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"";
            requestBody += " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"";
            requestBody += " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
            requestBody += "<soapenv:Body>";
            requestBody += "<q0:sayHello xmlns:q0=\"http://impl.webservice.scs.credithc.com/\">";
            requestBody += "<city>abcd</city>";
//            requestBody += "<templateId>" + templateId + "</templateId> ";
//            requestBody += "<isSeal>" + isSeal + "</isSeal> ";
//            requestBody += "<strJson>" + strJson + "</strJson> ";
//            requestBody += "<callBackUrl>" + callBackUrl + "</callBackUrl> ";
            requestBody += "</q0:sayHello>";
            requestBody += "</soapenv:Body>";


            // 6，通过流的方式将请求体发送出去：
            OutputStream out = con.getOutputStream();
            out.write(requestBody.getBytes());
            out.close();
            // 7，服务端返回正常：
            int code = con.getResponseCode();
            if (code == 200) {// 服务端返回正常
                InputStream is = con.getInputStream();
                byte[] b = new byte[1024];
                StringBuffer sb = new StringBuffer();
                int len = 0;
                while ((len = is.read(b)) != -1) {
                    String str = new String(b, 0, len, "UTF-8");
                    sb.append(str);
                }
                System.out.println(sb.toString());
                is.close();
            }
            con.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
