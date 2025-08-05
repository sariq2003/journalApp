package com.journalApp.service.serviceImpl;

import com.journalApp.entity.JournalEntry;
import com.journalApp.exception.ResourceNotFoundException;
import com.journalApp.payload.JournalEntryDto;
import com.journalApp.repository.JournalEntryRepository;
import com.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {


    @Autowired
    JournalEntryRepository journalRepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public JournalEntryDto saveEntry(JournalEntryDto journalEntryDto) {

        journalEntryDto.setTime(LocalDateTime.now());
        JournalEntry savedEntity = (JournalEntry)journalRepo.save(mapToEntity(journalEntryDto));

       return mapToDto(savedEntity);
    }

    @Override
    public List<JournalEntryDto> getAllEntries() {
        List<JournalEntry> journalEntries = journalRepo.findAll();
        List<JournalEntryDto> journalEntryDtos = journalEntries.stream().map(journalEntry -> mapToDto(journalEntry)).collect(Collectors.toList());
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


    public JournalEntryDto mapToDto(JournalEntry journalEntry) {
        JournalEntryDto mappedtoDto = mapper.map(journalEntry, JournalEntryDto.class);
        return mappedtoDto;

    }

    public JournalEntry mapToEntity(JournalEntryDto journalEntryDto) {

        JournalEntry mappedToEntity = mapper.map(journalEntryDto, JournalEntry.class);
        return mappedToEntity;
    }


}
