package com.demo.example;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.List;

/**
 * Created by louis on 2014/9/2.
 */
public class AutoLoginWY {
    private static String url = "https://mail.126.com/entry/cgi/ntesdoor?df=mail126_letter&from=web&funcid=loginone&iframe=1&language=-1&passtype=1&product=mail126&verifycookie=-1&net=failed&style=-1&race=-2_-2_-2_db&uid=sunyameng_621@126.com&hid=10010102";

    public static void main(String[] args) {
        try(CloseableHttpClient httpClient= HttpClients.createDefault()){
            CookieStore cookieStore=new BasicCookieStore();

            HttpClientContext localContext=HttpClientContext.create();
            //Bind custom cookie store to the local context
            localContext.setCookieStore(cookieStore);

            HttpGet httpGet = new HttpGet(url);
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

            HttpUriRequest loginRequest = RequestBuilder.post().setUri(new URI(url))
                    .addParameter("username", "sunyameng_621@126.com")
                    .addParameter("password", "sym3621")
                    .build();
            try(CloseableHttpResponse httpResponse=httpClient.execute(loginRequest,localContext)) {
                System.out.println("Login from get:" + httpResponse.getStatusLine());
                HttpEntity entity=httpResponse.getEntity();
                System.out.println(EntityUtils.toString(entity));

                HttpGet httpGet1 = new HttpGet("http://mail.126.com/js6/main.jsp?sid=TAXYSBTTSlTFqHSmgiTTAmLLiAXImmyH&df=mail126_letter");
                try(CloseableHttpResponse response1=httpClient.execute(httpGet1,localContext)){
                    System.out.println("--------------");
                    System.out.println(response1.getStatusLine());
                    HttpEntity entity1=response1.getEntity();
                    System.out.println(EntityUtils.toString(entity1));
                }catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println("Post login cookies:");
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


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
