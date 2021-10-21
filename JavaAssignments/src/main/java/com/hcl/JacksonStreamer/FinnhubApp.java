package com.hcl.JacksonStreamer;

import java.net.MalformedURLException;
import java.net.URL;

public class FinnhubApp {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://finnhub.io/api/v1/news?category=general&minId=6945750&token=c4dsr3iad3ieqvqh1hcg");
            JsonStreamDataBinder reader = new JsonStreamDataBinder(url);
            int i=0;
            while (reader.hasNext()) {
                i++;
                System.out.println("Headline #"+i+": "+reader.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
