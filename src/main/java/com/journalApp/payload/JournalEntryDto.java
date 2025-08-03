package com.journalApp.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

import org.springframework.validation.annotation.Validated;

@Validated
public class JournalEntryDto {



  private ObjectId id;

  @NotNull
  @Size(min = 2, max = 100, message = "Title should be more than 2 characters")
   private  String title;

  @NotNull
  @Size(min = 5, message = "Content should have atleast 5 characters")
   private  String content;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
