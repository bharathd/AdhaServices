package com.app.adha.dao;
import java.util.List;


import com.app.adha.entity.User;

public interface UserDAO{
	List<User> getUserByPhoneNumber(String phoneNumber);
	List<User> getAllUsers();
    User getUserById(int userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    int maxRecordId();
    
}
