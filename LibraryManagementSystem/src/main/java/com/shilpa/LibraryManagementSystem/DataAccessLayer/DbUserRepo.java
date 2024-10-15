package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DbUserRepo extends MongoRepository<User,String> {

}
