package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.State;

public interface StateService {
	
	List<State> getAllStates();
	State getStateById(int stateId);
    void addState(State state);

}
