package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.Book;

import java.util.List;

public interface CustomBook {

    Book findById(String id);
    List<Book> getAllBooks();
    void createBook(Book book);
    boolean checkIsAvailable(String id);
    void deleteBook(String id);
    void updateAvailability(String id, boolean isAvailable);
}
