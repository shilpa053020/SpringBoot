package com.shilpa.LibraryManagementSystem.DataAccessLayer;

import com.shilpa.LibraryManagementSystem.Models.Book;
import com.shilpa.LibraryManagementSystem.Models.Lending;
import com.shilpa.LibraryManagementSystem.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LendingRepo implements CustomLending{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Lending createLending(Lending lending) {
        User user = mongoTemplate.findById(lending.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + lending.getUserId());
        }
        Book book = mongoTemplate.findById(lending.getBookId(), Book.class);
        if (book == null) {
            throw new RuntimeException("Book not found with ID: " + lending.getBookId());
        }
        return mongoTemplate.save(lending);
    }

    @Override
    public Lending findLendingById(String id) {
        try {
            Lending lending = mongoTemplate.findById(id, Lending.class);
            if (lending == null) {
                throw new RuntimeException("Lending not found with ID: " + id);
            }

            Book book = mongoTemplate.findById(lending.getBookId(), Book.class);
            User user = mongoTemplate.findById(lending.getUserId(), User.class);

            if (book == null) {
                throw new RuntimeException("Book not found with ID: " + lending.getBookId());
            }
            if (user == null) {
                throw new RuntimeException("User not found with ID: " + lending.getUserId());
            }

            return lending;
        } catch (Exception e) {
            throw new RuntimeException("Error finding lending: " + e.getMessage());
        }
    }

    @Override
    public void markAsReturned(String id) {
        Lending lending = mongoTemplate.findById(id, Lending.class);
        if(lending!=null){
            lending.setIsReturned(true);
            mongoTemplate.save(lending);
        } else {
            throw new RuntimeException("Lending not found with ID: " + id);
        }
    }

    @Override
    public List<Lending> findActiveLendings() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isReturned").is(false));
        return mongoTemplate.find(query, Lending.class);
    }
}
