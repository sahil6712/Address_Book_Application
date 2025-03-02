package com.example.addressbookapp.controller;

import com.example.addressbookapp.dto.AddressBookEntryDTO;
import com.example.addressbookapp.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    // GET all contacts
    @GetMapping
    public ResponseEntity<List<AddressBookEntryDTO>> getAllContacts() {
        List<AddressBookEntryDTO> contacts = addressBookService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    // GET contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntryDTO> getContactById(@PathVariable int id) {
        AddressBookEntryDTO contact = addressBookService.getContactById(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // POST create a new contact
    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody AddressBookEntryDTO contactDTO) {
        addressBookService.createContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact added successfully");
    }

    // PUT update contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable int id, @RequestBody AddressBookEntryDTO updatedContactDTO) {
        boolean updated = addressBookService.updateContact(id, updatedContactDTO);
        return updated ? ResponseEntity.ok("Contact updated successfully") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
    }

    // DELETE contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        boolean deleted = addressBookService.deleteContact(id);
        return deleted ? ResponseEntity.ok("Contact deleted successfully") : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
    }
}
