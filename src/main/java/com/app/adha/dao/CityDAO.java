package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.City;

public interface CityDAO {
	
	List<City> getAllCitys();
	City getCityById(int cityId);
    void addCity(City city);
    void deleteCity(int cityId);
}
