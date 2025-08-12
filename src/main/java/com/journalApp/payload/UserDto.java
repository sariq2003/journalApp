package com.journalApp.payload;

import com.journalApp.entity.JournalEntry;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {


    private ObjectId id;

    @NotNull(message = "Username must not be null")
    @Size(min = 2,max = 15,message = "Username should have min 2 characters and max 15 character")
    private String userName;

    @NotNull
    @Size(min=4,message = "Password should atleast have 4 characters")
    private String password;


    private List<JournalEntry> journalEntries = new ArrayList<>();


}



