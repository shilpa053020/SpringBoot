package com.shilpa.LibraryManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Lending")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lending {
    @Id
    private String id;

    private String bookId;
    private String userId;

    private LocalDate lendingDate;
    private LocalDate returnDate;

    private Boolean isReturned = false;


}
