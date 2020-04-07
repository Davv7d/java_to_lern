package com.company;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebPage {
    public WebPage(){}

    public String GetDataFromWebPage(String address){
        try {
            URL url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("User-Agent", "Mozilla");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    public void  SendDataToWebPage(String data, String address){
        URL url = null;
        try {
            url = new URL(address);
            URLConnection conn = null;
            conn = url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("User-Agent", "Mozilla");

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

//            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
