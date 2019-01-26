package com.shavar.weather.sample;

public final class Weather {
    private String cityName;
    private double temp;

    public Weather(String cityName,double temp) {
        this.cityName = cityName;
        this.temp = temp;
    }

    public String getCityName(){
        return cityName;
    }

    public double getTemp(){
        return temp;
    }

}
