package com.shilpa.LibraryManagementSystem.DataAccessLayer;


import com.shilpa.LibraryManagementSystem.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserImplementation implements CustomUser {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> findAllUsers() {

        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User findById(String id) {

        return mongoTemplate.findById(id, User.class);
    }

    @Override
    public void createUser(User user) {

        mongoTemplate.save(user);
    }

    @Override
    public void updateUser(User user) {
        User existingUser = mongoTemplate.findById(user.getId(), User.class);
        if (existingUser == null) {
            throw new EmptyResultDataAccessException("User not found for ID: " + user.getId(), 1);
        }

        mongoTemplate.save(user);
    }

    @Override
    public void deleteUser(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
}
