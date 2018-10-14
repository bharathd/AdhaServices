package com.app.adha.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.adha.entity.State;

@Transactional
@Repository
public class StateDAOImpl implements StateDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public State getStateById(int stateId) {
		return entityManager.find(State.class, stateId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<State> getAllStates() {
		String hql = "FROM Otp as o ORDER BY o.userId";
		return (List<State>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addState(State state) {
		entityManager.persist(state);
	}

}
