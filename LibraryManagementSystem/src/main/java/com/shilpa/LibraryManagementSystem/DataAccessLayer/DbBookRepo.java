package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DbBookRepo extends MongoRepository<Book,String> {
}
