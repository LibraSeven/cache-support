package com.demo.httpclient;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.ws.spi.http.HttpContext;
import java.util.List;


/**
 * Created by louis on 2014/9/1.
 */
public class ClientCustomContext {
    public static void main(String[] args) {

        try(CloseableHttpClient httpClient= HttpClients.createDefault()){
            CookieStore cookieStore=new BasicCookieStore();

            HttpClientContext localContext=HttpClientContext.create();
            //Bind custom cookie store to the local context
            localContext.setCookieStore(cookieStore);

            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            System.out.println("Executing request:" + httpGet.getRequestLine());

            //Pass local context as a parameter
            try (CloseableHttpResponse response = httpClient.execute(httpGet, localContext)){
                System.out.println("--------------");
                System.out.println(response.getStatusLine());
                List<Cookie> cookies=cookieStore.getCookies();
                for (Cookie cookie : cookies) {
                    System.out.println("Local cookie:"+cookie);
                }
                EntityUtils.consume(response.getEntity());
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
