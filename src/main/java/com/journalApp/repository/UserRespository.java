package com.journalApp.repository;

import com.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRespository extends MongoRepository<User, ObjectId> {

    public Optional<User> getUserByuserName(String username);

}
