package com.shilpa.LibraryManagementSystem.Services;

import com.shilpa.LibraryManagementSystem.DataAccessLayer.CustomBook;
import com.shilpa.LibraryManagementSystem.Models.Book;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private CustomBook customBook;

    public List<Book> getAllBooks(){
        try{
            return customBook.getAllBooks();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to get:" +e.getMessage());
        }
    }

    public String createBook(Book book) {
        try {
            customBook.createBook(book);
            return "Book saved successfully!";
        } catch (Exception e) {
            System.err.println("Error while saving book: " + e.getMessage());
            return "Failed to save book: " + e.getMessage();
        }
    }

    public String deleteBook(String id) {
        try {
            customBook.deleteBook(id);
            return "Deleted Successfully!";
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to delete" + e.getMessage());
        }

    }

    public Book findById(String id){
        try{
            Book book = customBook.findById(id);
            if (book == null) {
                throw new RuntimeException("Book not found with ID: " + id);
            }
            return book;
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to get:" +e.getMessage());
        }
    }

    public Boolean checkBookIsAvailable(String id) {
        try {
            Boolean isAvailable = customBook.checkIsAvailable(id);
            if (isAvailable == null) {
                throw new RuntimeException("Book not found with ID: " + id);
            }
            return isAvailable;
        } catch (Exception e) {
            throw new RuntimeException("Failed to check availability for book with ID: " + id + ". Error: " + e.getMessage());
        }
    }

    public String updateAvailability(String id, Boolean isAvailable) {
        try {
            Book book = customBook.findById(id);
            if (book == null) {
                throw new RuntimeException("Book not found with ID: " + id);
            }

            customBook.updateAvailability(id, isAvailable);
            return "Availability updated successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Failed to update availability for book with ID: " + id + ". Error: " + e.getMessage());
        }
    }






}
