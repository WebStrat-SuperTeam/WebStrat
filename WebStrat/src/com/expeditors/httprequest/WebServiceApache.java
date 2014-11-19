package com.expeditors.httprequest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Uses Apache HttpComponents library: http://hc.apache.org/.
 * Dependencies: httpclient-4.3.6, httpcore-4.3.3, commons-logging-1.1.3
 * @author chq-emmam
 *
 */

public class WebServiceApache
{
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        try {
          // specify the host, protocol, and port
          HttpHost target = new HttpHost("finance.yahoo.com", 80, "http");
           
          // specify the get request
          HttpGet getRequest = new HttpGet("/d/quotes.csv?s=RHT+MSFT&f=sb2b3jk");
          
          // send request to the target and store host response in entity var
          HttpResponse httpResponse = httpclient.execute(target, getRequest);
          HttpEntity entity = httpResponse.getEntity();
     
          // print entity var to screen as string
          if (entity != null) {
            System.out.println(EntityUtils.toString(entity));
          }
     
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          // when HttpClient instance is no longer needed,
          // shut down the connection manager to ensure
          // immediate deallocation of all system resources
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      }
}
