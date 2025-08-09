package com.journalApp.service;

import com.journalApp.payload.JournalEntryDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface JournalEntryService {

    public JournalEntryDto saveEntry(JournalEntryDto journalEntryDto, String username);

    public List<JournalEntryDto> getAllEntriesOfUser(String username);

    public JournalEntryDto getEntryById(ObjectId id);


    public JournalEntryDto updateEntryById(ObjectId id , JournalEntryDto journalEntryDto);

    public void deleteEntryById(String userName,ObjectId id);




}
