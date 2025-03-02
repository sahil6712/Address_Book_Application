package com.example.addressbookapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private List<String> contacts = new ArrayList<>();

    // GET all contacts
    @GetMapping
    public ResponseEntity<List<String>> getAllContacts() {
        return ResponseEntity.ok(contacts);
    }

    // GET contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getContactById(@PathVariable int id) {
        if (id >= 0 && id < contacts.size()) {
            return ResponseEntity.ok(contacts.get(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
    }

    // POST create a new contact
    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody String contact) {
        contacts.add(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact added successfully");
    }

    // PUT update contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable int id, @RequestBody String updatedContact) {
        if (id >= 0 && id < contacts.size()) {
            contacts.set(id, updatedContact);
            return ResponseEntity.ok("Contact updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
    }

    // DELETE contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        if (id >= 0 && id < contacts.size()) {
            contacts.remove(id);
            return ResponseEntity.ok("Contact deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
    }
}