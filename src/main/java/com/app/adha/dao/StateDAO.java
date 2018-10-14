package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.State;

public interface StateDAO {
	
	List<State> getAllStates();
	State getStateById(int stateId);
    void addState(State state);

}
