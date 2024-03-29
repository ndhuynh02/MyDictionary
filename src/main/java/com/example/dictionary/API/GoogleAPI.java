package com.example.dictionary.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleAPI {
  // How To Use
/*
  public static void main(String[] args) throws IOException {
    System.out.println(translateFromEnToVi("Hello World"));
  }

 */


  public static String translateFromEnToVi(String input) throws IOException {
    return translate("en", "vi", input);
  }

  public static String translateFromViToEn(String input) throws IOException {
    return translate("vi", "en", input);
  }

  private static String translate(String langFrom, String langTo, String text) throws IOException {
    String urlStr =
        "https://script.google.com/macros/s/AKfycbw7Ia3oyzEeWRcvSAwC6BN-f8CcqQxduxBeIGSq_IPviuaq2LvrDbJaYrpOFP6x1_dL/exec"
            + "?q="
            + URLEncoder.encode(text, StandardCharsets.UTF_8)
            + "&target="
            + langTo
            + "&source="
            + langFrom;
    URL url = new URL(urlStr);
    StringBuilder response = new StringBuilder();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    return response.toString();
  }
}
