package com.app.adha.service;

import com.app.adha.entity.User;
import java.util.List;

public interface UserService {
	
	List<User> getUserByPhoneNumber(String phoneNumber);
	List<User> getAllUsers();
    User getUserById(int userId);
    String addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    int maxRecordId();
}
