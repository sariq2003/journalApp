package com.journalApp.service.serviceImpl;

import com.journalApp.entity.JournalEntry;
import com.journalApp.entity.User;
import com.journalApp.exception.ResourceNotFoundException;
import com.journalApp.payload.JournalEntryDto;
import com.journalApp.payload.UserDto;
import com.journalApp.repository.JournalEntryRepository;
import com.journalApp.repository.UserRespository;
import com.journalApp.service.JournalEntryService;
import com.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserRespository userRespository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto saveUser(UserDto userDto) {

        User savedUser = userRespository.save(mapToEntity(userDto));
        return mapToDto(savedUser);
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
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRespository.findAll();
        List<UserDto> collect = allUsers.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public UserDto getUserByusername(String userName) {
        Optional<User> userFromDb = userRespository.getUserByuserName(userName);
        if (userFromDb.isPresent()){
            return mapToDto(userFromDb.get());
        }else {
            throw new ResourceNotFoundException("User with username"+userName+"not found");
        }
    }

    public UserDto mapToDto(User user) {
        UserDto mappedtoDto = mapper.map(user, UserDto.class);
        return mappedtoDto;

    }

    public User mapToEntity(UserDto userDto) {

        User mappedToEntity = mapper.map(userDto, User.class);
        return mappedToEntity;
    }


}
