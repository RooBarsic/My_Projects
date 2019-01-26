package newModule;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.jws.WebResult;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestModule {
    public static void main(String[] args){
        try{
            System.out.println(translate("en-ru", "book"));
            //testURL_Connection();
        } catch (Exception e){
            System.out.println(" Owibka ");
            e.printStackTrace();
        }
    }

    public static String translateit(String en){
        try {
            return translate("en-ru", en);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("  yandex translation eror!! ");
        }
        return "????";
    }

    private static String translate(String lang, String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20181230T200250Z.9595ea94b6fbcd4b.8c5e2a63c1d01bfba398850cee06b5d566060a1c";
        URL urlObj = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        return translated;
    }
}
