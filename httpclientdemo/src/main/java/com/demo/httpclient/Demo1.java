package com.demo.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by louis on 2014/8/30.
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient= HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        CloseableHttpResponse response = httpClient.execute(httpGet);

        System.out.println(response.getStatusLine());

        HttpEntity entity=response.getEntity();
        EntityUtils.consume(entity);

        response.close();
        httpClient.close();
    }
}
