package controller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;

public class WebPriceCrawler extends PriceCrawler{
    private HttpsURLConnection sslURLConnection;
    private HttpURLConnection httpURLConnection;

    private String htmlString;
    private String itemName;
    private String itemPrice;

    public WebPriceCrawler(String url) throws IOException {
        findPrice(url);
    }

    public String getItemName(){
        return this.itemName;
    }

    public void findPrice(String url) throws IOException{
        if(url.contains("https")) {
            sslGetRequest(url);
        }else
            httpGetRequest(url);
    }

    private void httpGetRequest(String url) throws MalformedURLException,
            ProtocolException, IOException {
        try {
            URL myurl = new URL(url);
            httpURLConnection = (HttpURLConnection) myurl.openConnection();

            httpURLConnection.setRequestMethod("GET");

            StringBuilder content = readHTMLCode(httpURLConnection);
            htmlString = content.toString();
        } finally {
            httpURLConnection.disconnect();
        }

        //System.out.println(htmlString);

        store(url, htmlString);
    }

    private void sslGetRequest(String url) throws MalformedURLException,
            ProtocolException, IOException {
        try {
            URL myurl = new URL(url);
            sslURLConnection = (HttpsURLConnection) myurl.openConnection();

            sslURLConnection.setRequestMethod("GET");

            StringBuilder content = readHTMLCode(sslURLConnection);

            htmlString = content.toString();
        } finally {
            sslURLConnection.disconnect();
        }
        // System.out.println(htmlString);
        store(url, htmlString);
    }

    private StringBuilder readHTMLCode(HttpsURLConnection sslURLConnection) throws IOException {
        StringBuilder content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(sslURLConnection.getInputStream()))) {
            content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }
        return content;
    }

    private StringBuilder readHTMLCode(HttpURLConnection htmlURLConnection) throws IOException {
        StringBuilder content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(htmlURLConnection.getInputStream()))) {

            String line;
            content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }
        return content;
    }

    private void store(String url, String htmlString){
        if (url.contains("ebay.com")) {
            ebayExtractItemInfo(htmlString);
        } else if (url.contains("bestbuy.com")) {
            bestBuyExtractItemInfo(htmlString);
        } else if (url.contains("walmart.com")) {
            walmartExtractInfo(htmlString);
        } else if (url.contains("utep.edu")){
            utepExtractInfo(htmlString);
        }
    }

    private void bestBuyExtractItemInfo(String htmlString) {
        for (String word : htmlString.split("\\R")) {
            if (word.contains("<title>")) {
                itemName = word.substring(word.indexOf("<title>") + 7, word.indexOf("</title>"));
                System.out.println(itemName);
            } else if (word.contains("div class=\"priceView-hero-price priceView-purchase-price\"><span aria-label=\"Your price for this item is ")) {
                itemPrice = word.substring(word.indexOf("this item is ") + 13, word.indexOf("\\\"" + ">$\\u003c!"));
                System.out.println(itemPrice);
            }
        }
    }

    private void walmartExtractInfo(String htmlString) {
        for (String word : htmlString.split("\\R")) {
            if (word.contains("<title>")) {
                itemName = word.substring(word.indexOf("<title>")+ 7, word.indexOf("</title>"));
                System.out.println(itemName);
            }
            if (word.contains("<span class=\"price-characteristic\" itemprop=\"price\" content=\"")) {
                String sub = word.substring(word.indexOf("<span class=\"price-characteristic\" itemprop=\"price\" content=\""));
                sub = sub.substring(sub.indexOf("ent=\""));
                itemPrice = sub.substring(5, sub.indexOf("\">"));
                System.out.println(itemPrice);
            }

        }
    }

    private void utepExtractInfo(String htmlString) {
        for (String word : htmlString.split("\\R")) {
            if (word.contains("<title>")) {
                itemName = word.substring(word.indexOf("<title>") + 7, word.indexOf("</title>"));
                System.out.println(itemName);
            } else if (word.contains("<span style=\"color: blue\">")) {
                itemPrice = word.substring(word.indexOf("blue\">") + 7, word.indexOf("</span>"));
                System.out.println(itemPrice);
            }
        }

    }

    private void ebayExtractItemInfo(String htmlString) {
        for (String word : htmlString.split("\\R")) {
            if (word.contains("<title>")) {
                itemName = word.substring(word.indexOf("<title>") + 7, word.indexOf("</title>"));
                System.out.println(itemName);
            } else if (word.contains("<span class=\"notranslate\" id=\"prcIsum\" itemprop=\"price\"  style=\"\" content=\"")) {
                itemPrice = word.substring(word.indexOf("content=\"") + 9, word.indexOf("\">"));
                System.out.println(itemPrice);
            }
        }
    }








}
