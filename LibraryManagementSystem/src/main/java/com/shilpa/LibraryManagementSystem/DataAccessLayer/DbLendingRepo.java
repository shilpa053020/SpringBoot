package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.Lending;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DbLendingRepo extends MongoRepository<Lending,String> {
}
