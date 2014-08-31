package com.demo.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by louis on 2014/8/31.
 */
public class ClientConnectionRelease {
    public static void main(String[] args) {
        try(CloseableHttpClient httpClient=HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            System.out.println("Executing request:" + httpGet.getRequestLine());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
