package com.journalApp.service;

import com.journalApp.JournalApplication;
import com.journalApp.entity.JournalEntry;
import com.journalApp.payload.JournalEntryDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface JournalEntryService {

    public JournalEntryDto saveEntry(JournalEntryDto journalEntryDto);

    public List<JournalEntryDto> getAllEntries();

    public JournalEntryDto getEntryById(ObjectId id);

    public void deleteEntryById(ObjectId id);

    public JournalEntryDto updateEntryById(ObjectId id , JournalEntryDto journalEntryDto);





}
