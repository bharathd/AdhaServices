package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Otp;

@Transactional
@Repository
public class OtpDAOImpl implements OtpDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public Otp getOtpById(int userId) {
		return entityManager.find(Otp.class, userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Otp> getAllOtps() {
		String hql = "FROM Otp as o ORDER BY o.userId";
		return (List<Otp>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addOtp(Otp otp) {
		entityManager.persist(otp);
	}
	

}
