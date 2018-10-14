package com.app.adha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.StateDAO;
import com.app.adha.entity.State;

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateDAO stateDAO;
	
	@Override
	public State getStateById(int stateId) {
		State state = stateDAO.getStateById(stateId);
		return state;
	}	
	
	@Override
	public List<State> getAllStates(){
		return stateDAO.getAllStates();
	}
	
	@Override
	public void addState(State state){
		stateDAO.addState(state);
	}

}
