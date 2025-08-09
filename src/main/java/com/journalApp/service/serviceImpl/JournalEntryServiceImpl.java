package com.journalApp.service.serviceImpl;

import com.journalApp.entity.JournalEntry;
import com.journalApp.entity.User;
import com.journalApp.exception.ResourceNotFoundException;
import com.journalApp.payload.JournalEntryDto;
import com.journalApp.repository.JournalEntryRepository;
import com.journalApp.repository.UserRespository;
import com.journalApp.service.JournalEntryService;
import com.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {

    @Autowired
    UserRespository UserRespository;

    @Autowired
    UserService userService;

    @Autowired
    JournalEntryRepository journalRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public JournalEntryDto saveEntry(JournalEntryDto journalEntryDto, String username) {

        journalEntryDto.setTime(LocalDateTime.now());

        JournalEntry savedEntity = (JournalEntry)journalRepo.save(mapToEntity(journalEntryDto));
        User user = userService.getUserByusername(username);
        user.getJournalEntries().add(savedEntity);
        userService.saveUser(user);

        return mapToDto(savedEntity);
    }

    @Override
    public List<JournalEntryDto> getAllEntriesOfUser(String username) {
        User userFromDb = userService.getUserByusername(username);
        List<JournalEntryDto> journalEntryDtos = userFromDb.getJournalEntries().stream().map(journalEntry -> mapToDto(journalEntry)).collect(Collectors.toList());
        return journalEntryDtos;
    }

    @Override
    public JournalEntryDto getEntryById(ObjectId id) {
        JournalEntry journalEntry = journalRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Entry with id "+id+" not found"));
        return mapToDto(journalEntry);
    }

    @Override
    public void deleteEntryById(ObjectId id) {
        JournalEntry entry = journalRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Entry with id "+id+" not found"));
          journalRepo.delete(entry);
    }

    @Override
    public JournalEntryDto updateEntryById(ObjectId id, JournalEntryDto journalEntryDto) {
        JournalEntry journalEntry = journalRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entry with id " +id+ " not found"));

       journalEntry.setTitle(journalEntryDto.getTitle()!=null&& !journalEntryDto.getTitle().equals("")
               ?journalEntryDto.getTitle():journalEntry.getTitle());

        journalEntry.setContent(journalEntryDto.getContent()!=null && !journalEntryDto.getContent().equals("")
                ?journalEntryDto.getContent():journalEntry.getContent());
        JournalEntry entry = journalRepo.save(journalEntry);
        return mapToDto(entry);
    }

    @Override
    public void deleteEntriesByUser(String userName) {
        User userFromDb= userService.getUserByusername(userName);
            userFromDb.getJournalEntries().forEach(entry -> journalRepo.deleteById(entry.getId()));
        }

    public JournalEntryDto mapToDto(JournalEntry journalEntry) {
        JournalEntryDto mappedtoDto = mapper.map(journalEntry, JournalEntryDto.class);
        return mappedtoDto;

    }

    public JournalEntry mapToEntity(JournalEntryDto journalEntryDto) {

        JournalEntry mappedToEntity = mapper.map(journalEntryDto, JournalEntry.class);
        return mappedToEntity;
    }
}



