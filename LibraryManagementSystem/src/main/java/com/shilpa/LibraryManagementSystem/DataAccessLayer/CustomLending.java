package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.Lending;

import java.util.List;

public interface CustomLending {
    Lending findLendingById(String id);
    Lending createLending(Lending lending);
    List<Lending> findActiveLendings();
    void markAsReturned(String id);
}
