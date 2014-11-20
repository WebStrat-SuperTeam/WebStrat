package com.expeditors.httprequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RequestYQL
{    
    public static void main (String[] args)
    {
        String query = "select * from csv where url='http://download.finance.yahoo.com/d/quotes.csv?s=YHOO,GOOG,AAPL&f=sl1d1t1c1ohgv&e=.csv' and columns='symbol,price,date,time,change,col1,high,low,col2'";
        
        System.out.println("YQL XML OUTPUT=======================================");
        List<String> xml_msg = getXML(query);
        for (String str : xml_msg)
        {
            System.out.print(str);
        }
        System.out.println("\n======================================================\n");
        
        System.out.println("YQL JSON OUTPUT=======================================");
        List<String> json_msg = getJSON(query);
        for (String str : json_msg)
        {
            System.out.print(str);
        }
        System.out.println("\n======================================================\n");
    }
    
    /**
     * Private Constructor.
     */
    private RequestYQL() {}
    
    /**
     * This method takes a string query in YQL and returns a list of strings
     * that form an XML response message.
     * @param query - string in the form of a YQL query
     * @return list of strings that form an XML response
     */
    public static List<String> getXML (String oQuery)
    {
        List<String> msg = new ArrayList<>();

        try {
            String query = URLEncoder.encode(oQuery, "UTF-8");
            System.out.println("Query: " + query);      // DEBUG STATEMENT
            URL url = new URL("http://query.yahooapis.com/v1/public/yql?q="+ query + "&count=50&format=xml");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection
                    .getInputStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                    msg.add(inputLine);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return msg;
    }
    
    /**
     * This method takes a string query in YQL and returns a list of strings
     * that form a JSON response message.
     * @param query - string in the form of a YQL query
     * @return list of strings that form a JSON response
     */
    public static List<String> getJSON (String oQuery)
    {
        List<String> msg = new ArrayList<>();

        try {
            String query = URLEncoder.encode(oQuery, "UTF-8");
            System.out.println("Query: " + query);      // DEBUG STATEMENT
            URL url = new URL("http://query.yahooapis.com/v1/public/yql?q="+ query + "&count=50&format=json");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection
                    .getInputStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                    msg.add(inputLine);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return msg;
    }
}
