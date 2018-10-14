package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.adha.dao.CityDAO;
import com.app.adha.entity.City;

public class CityServiceImpl implements CityService{

	@Autowired
	private CityDAO cityDAO;
	
	@Override
	public City getCityById(int cityId) {
		City city = cityDAO.getCityById(cityId);
		return city;
	}	
	
	@Override
	public List<City> getAllCitys(){
		return cityDAO.getAllCitys();
	}
	
	@Override
	public void addCity(City city){
		cityDAO.addCity(city);
	}
}
