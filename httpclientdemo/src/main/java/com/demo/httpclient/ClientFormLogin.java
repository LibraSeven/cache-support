package com.demo.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.List;


/**
 * Created by louis on 2014/9/1.
 */
public class ClientFormLogin {
    public static void main(String[] args) {

        CookieStore cookieStore =new BasicCookieStore();

        try(CloseableHttpClient httpClient= HttpClients.custom()
                .setDefaultCookieStore(cookieStore).build()) {

            HttpGet httpGet = new HttpGet("http://192.168.0.10:8080/monitor/index.do");
            try(CloseableHttpResponse response=httpClient.execute(httpGet)){
                HttpEntity entity=response.getEntity();
                System.out.println("Login form get:" + response.getStatusLine());
                EntityUtils.consume(entity);

                System.out.println("Initial set of cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                }else {
                    for (Cookie cookie : cookies) {
                        System.out.println("- " + cookie);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }

            HttpUriRequest loginRequest = RequestBuilder.post().setUri(new URI("http://192.168.0.10:8080/monitor/login.do"))
                    .addParameter("username", "admin")
                    .addParameter("password", "123")
                    .build();
            try (CloseableHttpResponse response = httpClient.execute(loginRequest)){
                HttpEntity entity=response.getEntity();
                System.out.println("Login from get:" + response.getStatusLine());
                //EntityUtils.consume(entity);
                System.out.println(EntityUtils.toString(entity));
                System.out.println("Post login cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                }else {
                    for (Cookie cookie : cookies) {
                        System.out.println("- " + cookie);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
