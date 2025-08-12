package com.journalApp.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class JournalEntryDto {



  private ObjectId id;

  @NotNull
  @Size(min = 2, max = 100, message = "Title should be more than 2 characters")
   private  String title;

  @NotNull
  @Size(min = 5, message = "Content should have atleast 5 characters")
   private  String content;

   private LocalDateTime time;



}
