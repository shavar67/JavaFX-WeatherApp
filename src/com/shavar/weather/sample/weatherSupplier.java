package com.shavar.weather.sample;

import java.util.function.Supplier;

public interface weatherSupplier{
    Weather get(String cityName);

}
