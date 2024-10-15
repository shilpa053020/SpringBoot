package com.shilpa.LibraryManagementSystem.Controllers;

import com.shilpa.LibraryManagementSystem.Models.Lending;
import com.shilpa.LibraryManagementSystem.Services.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lendings")
public class LendingController {

    @Autowired
    private LendingService lendingService;

    @GetMapping("/{id}")
    public ResponseEntity<Lending> findLendingById(@PathVariable String id) {
        try {
            Lending lending = lendingService.findLendingById(id);
            return ResponseEntity.ok(lending);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Lending> createLending(@RequestBody Lending lending) {
        try {
            Lending newLending = lendingService.createLending(lending);
            return ResponseEntity.status(HttpStatus.CREATED).body(newLending);
        } catch (RuntimeException e) {
            System.err.println("Error creating lending: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/active")
    public ResponseEntity<List<Lending>> findActiveLendings() {
        try {
            List<Lending> activeLendings = lendingService.findActiveLendings();
            return ResponseEntity.ok(activeLendings);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<String> markAsReturned(@PathVariable("id") String id) {
        try {
            String message = lendingService.markAsReturned(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
