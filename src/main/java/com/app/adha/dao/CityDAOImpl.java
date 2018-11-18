package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.City;
import com.app.adha.util.UtilMethods;

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
		String hql = "FROM City as c ORDER BY c.cityId";
		return (List<City>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addCity(City city) {
		entityManager.persist(city);
	}
   
	@Override
	public void deleteCity(int cityId) {
		String update_query = "update City set status = :status where id = :id";
		entityManager.createQuery(update_query).setParameter("status", UtilMethods.DELETE).setParameter("id", cityId).executeUpdate();
	}
}
