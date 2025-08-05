package com.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "journal_Entries")
public class JournalEntry {

    @Id
    ObjectId id;

    String title;
    String content;

}
