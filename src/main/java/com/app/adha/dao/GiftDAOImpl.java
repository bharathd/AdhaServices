package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Gift;
import com.app.adha.util.UtilMethods;

@Transactional
@Repository
public class GiftDAOImpl implements GiftDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public Gift getGiftById(int giftId) {
		return entityManager.find(Gift.class, giftId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gift> getAllGifts() {
		String hql = "FROM Gift as g ORDER BY g.giftId";
		return (List<Gift>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addGift(Gift gift) {
		entityManager.persist(gift);
	}
	
	@Override
	public void deleteGift(int giftId) {
		String update_query = "update Gift set status = :status where id = :id";
		entityManager.createQuery(update_query).setParameter("status", UtilMethods.DELETE).setParameter("id", giftId).executeUpdate();
	}

}
