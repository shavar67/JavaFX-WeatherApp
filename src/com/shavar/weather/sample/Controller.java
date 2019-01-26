package com.shavar.weather.sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private JFXTextField searchField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label humid;
    @FXML
    private Label degLabel;
    @FXML
    private Label speed;
    @FXML
    private Label api;
    @FXML
    Button searchBtn;
    @FXML
    private Label high;

    @FXML
    private Label low;
    @FXML
  private Label cloud;

    private weatherSupplier weathersupplier;
    private weatherService service = new weatherService();

    public Controller(weatherSupplier supplier) {
	weathersupplier = supplier;
	
    }

    public void onSearch() {
	
	service.restart();
    }
       
    private class weatherService extends Service<Weather> {

	@Override
	protected Task<Weather> createTask() {
	    return new Task<Weather>() {
		@Override
		protected Weather call() throws Exception {
		    return weathersupplier.get(searchField.getText());
		}

		@Override
		protected void succeeded() {

		    String API_KEY = "e7dda5158a7819350afa06b7a49f9656";
		    String LOCATION = searchField.getText();
		    searchField.clear();
		    String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid="
			    + API_KEY + "&units=imperial";
		    try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
			    result.append(line);
			}
			rd.close();

			Map<String, Object> respMap = jsonToMap(result.toString());
			Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String, Object> winMap = jsonToMap(respMap.get("wind").toString());
			Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());
			Map<String, Object> cloudMap = jsonToMap(respMap.get("clouds").toString());

			DateFormat dt = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.ENGLISH);

			nameLabel.setText(
				"Location: " + LOCATION + " , " + sysMap.get("country") + "  " + dt.format(new Date()));
			degLabel.setText("Currently: " + (Math.ceil((double) mainMap.get("temp"))));
			humid.setText("Humidity: " + mainMap.get("humidity") + "%");
			speed.setText("Wind Speeds: " + winMap.get("speed") + " mph");
			high.setText("High: " + (Math.ceil((double) mainMap.get("temp_max"))));
			low.setText("low: " + (Math.ceil((double) mainMap.get("temp_min"))));
			cloud.setText("Clouds: " + (Math.ceil((double) cloudMap.get("all")) + "%"));

		    } catch (Exception exception) {
			exception.printStackTrace();

		    }

		}

		@Override
		protected void failed() {
		    Throwable error = getException();
		    nameLabel.setText("Error" + error);
		}
	    };
	}
    }

    public static Map<String, Object> jsonToMap(String string) {
	Map<String, Object> map = new Gson().fromJson(string, new TypeToken<HashMap<String, Object>>() {
	}.getType());
	return map;

    }
}
