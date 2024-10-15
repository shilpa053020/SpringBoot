package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.User;

import java.util.List;

public interface CustomUser {

    User findById(String id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(String id);
    List<User> findAllUsers();
}
