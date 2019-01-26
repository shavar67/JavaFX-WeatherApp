package com.shavar.weather.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OpenMapApi {

    public void connectApi() {

	String API_KEY = "e7dda5158a7819350afa06b7a49f9656";
	String LOCATION = "Baltimore";
	String urlString  ="http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
	try {
	    StringBuilder result = new StringBuilder();
	    URL url = new URL(urlString);
	    URLConnection conn = url.openConnection();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String line;
	    while((line = rd.readLine()) != null) {
		result .append(line);
	    }
	    rd.close();
	  
	   System.out.println(result);
	    
	    Map<String,Object> respMap = jsonToMap(result.toString());
	    Map<String,Object> mainMap = jsonToMap(respMap.get("main").toString());
	    Map<String,Object> winMap = jsonToMap(respMap.get("wind").toString());
	 
	    
	 
	    System.out.println("Current temp:" + mainMap.get("temp"));
	    System.out.println("Humidty:" + mainMap.get("humidity"));
	    System.out.println("wind Speeds:" + winMap.get("speed"));
	    System.out.println("wind angles:" + winMap.get("deg"));
	}
	catch(Exception exception) {
	    exception.printStackTrace();
	  
	    
	}
    }
    public static Map<String, Object> jsonToMap(String string) {
  	Map<String, Object> map = new Gson().fromJson(string, new TypeToken<HashMap<String, Object>>() {
  	}.getType());
  	return map;

      }
    
  
	
	 
     }

