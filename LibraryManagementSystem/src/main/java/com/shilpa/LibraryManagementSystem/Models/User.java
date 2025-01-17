package com.shilpa.LibraryManagementSystem.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "User")
@Data
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String mobileNumber;
    private List<Book> borrowedBooks;

}
