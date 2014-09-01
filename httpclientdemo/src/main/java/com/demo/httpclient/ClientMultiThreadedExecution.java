package com.demo.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


/**
 * Created by louis on 2014/9/1.
 */
public class ClientMultiThreadedExecution {
    public static void main(String[] args) {
        // Create an HttpClient with the ThreadSafeClientConnManager.
        // This connection manager must be used if more than one thread will
        // be using the HttpClient.
        PoolingHttpClientConnectionManager cm=new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);

        try(CloseableHttpClient httpClient= HttpClients.custom().setConnectionManager(cm).build()){
            // create an array of URIs to perform GETs on
            String[] urisToGet = {
                    "http://hc.apache.org/",
                    "http://hc.apache.org/httpcomponents-core-ga/",
                    "http://hc.apache.org/httpcomponents-client-ga/",
            };

            // create a thread for each URI
            GetThread[] threads = new GetThread[urisToGet.length];
            for (int i = 0; i < threads.length; i++) {
                HttpGet httpget = new HttpGet(urisToGet[i]);
                threads[i] = new GetThread(httpClient, httpget, i + 1);
            }
            // start the threads
            for (GetThread thread : threads) {
                thread.start();
            }

            // join the threads
            for (GetThread thread : threads) {
                thread.join();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static class GetThread extends Thread{
        private CloseableHttpClient httpClient;
        private HttpContext context;
        private HttpGet httpget;
        private int id;
        public GetThread(CloseableHttpClient httpClient, HttpGet httpget, int id) {
            this.httpClient = httpClient;
            this.context = new BasicHttpContext();
            this.httpget = httpget;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println(id + " - about to get something from " + httpget.getURI());
                try (CloseableHttpResponse response = httpClient.execute(httpget, context)) {
                    System.out.println(id + " - get executed");
                    // get the response body as an array of bytes
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        byte[] bytes = EntityUtils.toByteArray(entity);
                        System.out.println(id + " - " + bytes.length + " bytes read");
                    }
                }
            } catch (Exception e) {
                System.out.println(id + " - error: " + e);
            }
        }
    }
}
