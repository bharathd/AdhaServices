package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.City;

@Transactional
@Repository
public class CityDAOImpl implements CityDAO{

	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public City getCityById(int cityId) {
		return entityManager.find(City.class, cityId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<City> getAllCitys() {
		String hql = "FROM Otp as o ORDER BY o.userId";
		return (List<City>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addCity(City city) {
		entityManager.persist(city);
	}

}
