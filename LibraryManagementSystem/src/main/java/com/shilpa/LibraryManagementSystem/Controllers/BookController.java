package com.shilpa.LibraryManagementSystem.Controllers;

import com.shilpa.LibraryManagementSystem.Models.Book;
import com.shilpa.LibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks (){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book){
        String responseMessage = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id) {
        try {
            String responseMessage = bookService.deleteBook(id);
            return ResponseEntity.ok(responseMessage);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        try {
            Book book = bookService.findById(id);
            return ResponseEntity.ok(book);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/checkAvailability/{id}")
    public ResponseEntity<Boolean> checkBookIsAvailable(@PathVariable String id) {
        try {
            Boolean isAvailable = bookService.checkBookIsAvailable(id);
            return ResponseEntity.ok(isAvailable);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/updateAvailability/{id}")
    public ResponseEntity<String> updateBookAvailability(@PathVariable String id, @RequestParam Boolean isAvailable) {
        try {
            String message = bookService.updateAvailability(id, isAvailable);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update availability: " + e.getMessage());
        }
    }
}
