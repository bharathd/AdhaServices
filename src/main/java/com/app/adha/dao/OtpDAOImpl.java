package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Otp;
import com.app.adha.entity.UserDetails;

@Transactional
@Repository
public class OtpDAOImpl implements OtpDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Otp> getOtpByPhoneNumber(String phoneNumber) {
		List<Otp> resultList = (List<Otp>) entityManager.createQuery("from Otp where phone_number = :phone_number ").setParameter("phone_number", phoneNumber).getResultList();
		return resultList;
	}
	
	
   @Override
	public void addOrUpdateOtp(Otp otp) {
		List<Otp> list_otp = getOtpByPhoneNumber(otp.getPhoneNumber());
		if(list_otp.size()>0) {
			String update_query = "update Otp set otp_num = :otp_num where phone_number = :phone_number";
		    entityManager.createQuery(update_query).setParameter("otp_num", otp.getOtpNumber()).setParameter("phone_number", otp.getPhoneNumber()).executeUpdate();
		}else {
			entityManager.persist(otp);
	}}
	

}
