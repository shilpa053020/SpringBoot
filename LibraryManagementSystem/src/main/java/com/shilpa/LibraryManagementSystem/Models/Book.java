package com.shilpa.LibraryManagementSystem.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
     @Id
     private String id;

     private String title;
     private String author;
     private Boolean isAvailable = true;


}
