package com.shilpa.LibraryManagementSystem.Services;

import com.shilpa.LibraryManagementSystem.Models.Book;
import com.shilpa.LibraryManagementSystem.Models.Lending;
import com.shilpa.LibraryManagementSystem.Models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LendingDetailsResponse {
    private Lending lending;
    private User user;
    private Book book;

    public LendingDetailsResponse(Lending lending, User user, Book book) {
        this.lending = lending;
        this.user = user;
        this.book = book;
    }
}
