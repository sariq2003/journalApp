package com.journalApp.service.serviceImpl;

import com.journalApp.entity.User;
import com.journalApp.exception.ResourceNotFoundException;
import com.journalApp.repository.JournalEntryRepository;
import com.journalApp.repository.UserRespository;
import com.journalApp.service.JournalEntryService;
import com.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserRespository userRespository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Override
    public User saveUser(User user) {
        User savedUser = userRespository.save(user);
        return savedUser;
    }

    @Override
    public void deleteUserByusername(String userName) {
        Optional<User> userFromDb = userRespository.getUserByuserName(userName);
        if (userFromDb.isPresent()) {
            journalEntryRepository.deleteAll(userFromDb.get().getJournalEntries());
            userRespository.deleteById(userFromDb.get().getId());
        } else {

            throw new ResourceNotFoundException("User not found with username:" + userName);

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRespository.findAll();
        return allUsers;
    }

    @Override
    public User getUserByusername(String userName) {
        Optional<User> userFromDb = userRespository.getUserByuserName(userName);
        if (userFromDb.isPresent()){
            return userFromDb.get();
        }else {
            throw new ResourceNotFoundException("User with username"+userName+"not found");
        }
    }


}
