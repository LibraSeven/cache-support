package com.demo.httpclient;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by louis on 2014/9/1.
 */
public class ClientExecuteProxy {
    public static void main(String[] args) {
        try(CloseableHttpClient httpClient= HttpClients.createDefault()) {
            HttpHost target = new HttpHost("192.168.0.10", 8080, "http");
            HttpHost proxy=new HttpHost("192.168.0.15",80);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .build();
            HttpGet httpGet = new HttpGet("/test");
            httpGet.setConfig(requestConfig);
            System.out.println("Executing request:" + httpGet.getRequestLine()+" to "+target+" via "+proxy);

            try(CloseableHttpResponse response = httpClient.execute(target, httpGet)){
                System.out.println("-------------------");
                System.out.println(response.getStatusLine());
                EntityUtils.consume(response.getEntity());

            }catch (Exception e){
                e.printStackTrace();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
