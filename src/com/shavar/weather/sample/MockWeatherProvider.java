package com.shavar.weather.sample;

public class MockWeatherProvider implements weatherSupplier {

    @Override
    public Weather get(String cityName) {
        return new Weather(cityName, 76.0);
    }
}
