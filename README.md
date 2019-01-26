 # JavaFX-WeatherApp

* This application uses the open weather map api to fetch the current weather conditions for a given location.

 ### Libraries Used

* This project frontend was built using the material design jfoeinx library. -> http://www.jfoenix.com/index.html#start

* Data provided by. -> https://openweathermap.org


### What i learned

* How to use hashmaps to map json data to java objects via the google gson libary.
* How to fetch weather data using the openweather map api (will need to create an account and get an api key).
* Background threads used to process the api call in the background (prevents the main thread from freezing).

`<Map<String, Object> respMap = jsonToMap(result.toString());
			Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String, Object> winMap = jsonToMap(respMap.get("wind").toString());
			Map<String, Object> sysMap = jsonToMap(respMap.get("sys").toString());
			Map<String, Object> cloudMap = jsonToMap(respMap.get("clouds").toString()); >`

<img src="https://github.com/shavar67/JavaFX-WeatherApp/blob/master/src/com/shavar/weather/sample/weatherApp.jpg" width="256" height="256" title="Weather app">
