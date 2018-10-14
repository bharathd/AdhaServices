package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.City;

public interface CityService {

	List<City> getAllCitys();
	City getCityById(int cityId);
    void addCity(City city);
}
