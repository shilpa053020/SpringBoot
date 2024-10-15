package com.shilpa.LibraryManagementSystem.Services;

import com.shilpa.LibraryManagementSystem.DataAccessLayer.CustomUser;
import com.shilpa.LibraryManagementSystem.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private CustomUser customUser;

    public User findById(String id) {
        try {
            User user = customUser.findById(id);
            if (user == null) {
                throw new RuntimeException("User not found with ID: " + id);
            }
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error finding user: " + e.getMessage());
        }
    }

    public void createUser(User user) {
        try {
            customUser.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        try {
            customUser.updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Error updating user: " + e.getMessage());
        }
    }

    public void deleteUser(String id) {
        try {
            customUser.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }
    }

    public List<User> findAllUsers() {
        try {
            return customUser.findAllUsers();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all users: " + e.getMessage());
        }
    }
}
