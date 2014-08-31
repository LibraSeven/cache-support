package com.demo.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by louis on 2014/8/30.
 */
public class PostDemo {
    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient= HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://localhost:8080/public/deleteCoordinate");
        List<NameValuePair> list=new ArrayList<>();
        list.add(new BasicNameValuePair("panoid", "201306135p1349290"));
        httpPost.setEntity(new UrlEncodedFormEntity(list));

        CloseableHttpResponse response=httpClient.execute(httpPost);

        System.out.println(response.getStatusLine());

        HttpEntity entity = response.getEntity();

//        EntityUtils.consume(entity);

        System.out.println(EntityUtils.toString(entity));

        httpClient.close();
        response.close();


    }
}
