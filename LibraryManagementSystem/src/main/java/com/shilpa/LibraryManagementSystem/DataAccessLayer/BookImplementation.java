package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class BookImplementation implements  CustomBook{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Book findById(String id) {
        try {
            return mongoTemplate.findById(id,Book.class);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createBook(Book book) {
        // Check if a book with the same id already exists
        Query idQuery = new Query();
        idQuery.addCriteria(Criteria.where("id").is(book.getId()));

        Book existingBookById = mongoTemplate.findOne(idQuery, Book.class);
        if (existingBookById != null) {
            throw new RuntimeException("Book with ID '" + book.getId() + "' already exists.");
        }
        try{
            mongoTemplate.save(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(String id) {
         try{
             mongoTemplate.remove(query(where("id").is(id)), Book.class);
         } catch (RuntimeException e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    public List<Book> getAllBooks() {
        try{
            return mongoTemplate.findAll(Book.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean checkIsAvailable(String id) {
        try {
            Book book = mongoTemplate.findById(id, Book.class);
            return book != null && book.getIsAvailable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAvailability(String id, boolean isAvailable) {
        try {
            Book book = mongoTemplate.findById(id, Book.class);
            if (book != null) {
                book.setIsAvailable(isAvailable);
                mongoTemplate.save(book);
            } else {
                throw new RuntimeException("Book not found with ID: " + id);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
