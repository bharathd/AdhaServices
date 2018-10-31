package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.Payment;

@Transactional
@Repository
public class PaymentDAOImpl implements PaymentDAO{

	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public Payment getPaymentById(int paymentId) {
		return entityManager.find(Payment.class, paymentId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getAllPayments() {
		String hql = "FROM Payment as p ORDER BY p.paymentId";
		return (List<Payment>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addPayment(Payment payment) {
		entityManager.persist(payment);
	}
}
