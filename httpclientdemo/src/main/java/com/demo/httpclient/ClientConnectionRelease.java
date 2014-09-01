package com.demo.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by louis on 2014/8/31.
 */
public class ClientConnectionRelease {
    public static void main(String[] args) {
        try(CloseableHttpClient httpClient=HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            System.out.println("Executing request:" + httpGet.getRequestLine());
            try(CloseableHttpResponse response=httpClient.execute(httpGet)) {
                System.out.println("------------");
                System.out.println(response.getStatusLine());

                //Get hold of the response entity
                HttpEntity entity=response.getEntity();
                if (entity != null) {
                    InputStream inputStream = entity.getContent();
                    byte[] buffer = new byte[1024];
                    int p=-1;
                    while ((p = inputStream.read(buffer)) != -1) {
                        String temp = new String(buffer, 0, buffer.length, Charset.forName("UTF-8"));
                        System.out.println(temp);
                    }
                }
            }catch (Exception e){
                // In case of an IOException the connection will be released
                // back to the connection manager automatically
                e.printStackTrace();
                throw e;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
