package com.journalApp.controller;

import com.journalApp.payload.JournalEntryDto;
import com.journalApp.service.JournalEntryService;
import com.journalApp.service.serviceImpl.JournalEntryServiceImpl;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/journalEntry")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    //http://localhost:8080/api/journalEntry/saveEntry?username=
    @PostMapping("/saveEntry")
    public ResponseEntity<?> saveEntry(@Valid @RequestBody JournalEntryDto journalEntryDto, BindingResult result,@RequestParam String username) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        JournalEntryDto journalEntryDto1 = journalEntryService.saveEntry(journalEntryDto,username);
        return new ResponseEntity<>(journalEntryDto1, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/journalEntry/getAllEntriesOfUser/{userName}
    @GetMapping("/getAllEntriesOfUser/{userName}")
    public ResponseEntity<List<JournalEntryDto>> getAll(@PathVariable String userName) {
        List<JournalEntryDto> journalDtos = journalEntryService.getAllEntriesOfUser(userName);
        return new ResponseEntity<>(journalDtos, HttpStatus.OK);
    }


    //http://localhost:8080/api/journalEntry/getByid/{id}
    @GetMapping("/getByid/{id}")
    public ResponseEntity<JournalEntryDto> getEntryById(@PathVariable ObjectId id) {
        JournalEntryDto entryById = journalEntryService.getEntryById(id);
        return new ResponseEntity<>(entryById, HttpStatus.OK);

    }
// http://localhost:8080/api/journalEntry/updateEntry/{id}
    @PutMapping("/updateEntry/{id}")
    public ResponseEntity<JournalEntryDto> updateById(@PathVariable ObjectId id, @RequestBody JournalEntryDto journalEntryDto) {
        JournalEntryDto journalEntryDto1 = journalEntryService.updateEntryById(id, journalEntryDto);
        return new ResponseEntity<>(journalEntryDto1,HttpStatus.OK);
    }

    //http://localhost:8080/api/journalEntry/deleteEntry/{userName}
    @DeleteMapping("deleteEntry/{userName}/{EntryId}")
    public ResponseEntity<?> deleteEntriesByUser(@PathVariable String userName, @PathVariable ObjectId EntryId){
         journalEntryService.deleteEntryById(userName,EntryId);
         return new ResponseEntity<>("Successfully Deleted",HttpStatus.NO_CONTENT);

    }







}
