package com.shilpa.LibraryManagementSystem.Services;

import com.shilpa.LibraryManagementSystem.DataAccessLayer.CustomBook;
import com.shilpa.LibraryManagementSystem.DataAccessLayer.CustomLending;
import com.shilpa.LibraryManagementSystem.DataAccessLayer.CustomUser;
import com.shilpa.LibraryManagementSystem.Models.Book;
import com.shilpa.LibraryManagementSystem.Models.Lending;
import com.shilpa.LibraryManagementSystem.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LendingService {
    @Autowired
    private CustomLending customLending;
    @Autowired
    private CustomUser customUser;
    @Autowired
    private CustomBook customBook;

    public LendingDetailsResponse findLendingById(String id) {
        try {
            // Fetch Lending by ID
            Lending lending = customLending.findLendingById(id);
            if (lending == null) {
                throw new RuntimeException("Lending not found with ID: " + id);
            }

            // Fetch User by userId
            User user =customUser .findById(lending.getUserId());
            if (user == null) {
                throw new RuntimeException("User not found with ID: " + lending.getUserId());
            }

            // Fetch Book by bookId
            Book book = customBook.findById(lending.getBookId());
            if (book == null) {
                throw new RuntimeException("Book not found with ID: " + lending.getBookId());
            }
            // Return combined details as a response (or process as needed)
            return new LendingDetailsResponse(lending, user, book);

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

