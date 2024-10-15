package com.shilpa.LibraryManagementSystem.Services;

import com.shilpa.LibraryManagementSystem.DataAccessLayer.CustomLending;
import com.shilpa.LibraryManagementSystem.Models.Lending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LendingService {
    @Autowired
    private CustomLending customLending;

    public Lending findLendingById(String id) {
        try {
            Lending lending = customLending.findLendingById(id);
            if (lending == null) {
                throw new RuntimeException("Lending not found with ID: " + id);
            }
            return lending;
        } catch (Exception e) {
            throw new RuntimeException("Error finding lending: " + e.getMessage());
        }
    }

    public Lending createLending(Lending lending) {
        try {
            return customLending.createLending(lending);
        } catch (Exception e) {
            throw new RuntimeException("Error creating lending: " + e.getMessage());
        }
    }

    public List<Lending> findActiveLendings() {
        try {
            return customLending.findActiveLendings();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving active lendings: " + e.getMessage());
        }
    }

    public String markAsReturned(String id) {
        try {
            Lending lending = customLending.findLendingById(id);
            if (lending == null) {
                throw new RuntimeException("Lending not found with ID: " + id);
            }
            customLending.markAsReturned(id);
            return "Lending marked as returned.";
        } catch (Exception e) {
            throw new RuntimeException("Error marking as returned: " + e.getMessage());
        }
    }

}

