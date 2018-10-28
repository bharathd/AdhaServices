package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.CityDAO;
import com.app.adha.entity.City;
import com.app.adha.util.UtilMethods;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private UtilMethods utilMethod;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
	
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
		city.setCreatedDate(df.format(dateobj));
		city.setStatus(utilMethod.ACTIVE);
		cityDAO.addCity(city);
	}
	
	@Override
	public void deleteCity(int cityId) {
		cityDAO.deleteCity(cityId);
	}
}
