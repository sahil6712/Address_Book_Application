package com.example.addressbookapp.controller;

import com.example.addressbookapp.dto.AddressBookEntryDTO;
import com.example.addressbookapp.model.AddressBookEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private List<AddressBookEntry> contacts = new ArrayList<>();

    // GET all contacts
    @GetMapping
    public ResponseEntity<List<AddressBookEntryDTO>> getAllContacts() {
        List<AddressBookEntryDTO> contactDTOs = contacts.stream()
                .map(contact -> new AddressBookEntryDTO(contact.getName(), contact.getPhone()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(contactDTOs);
    }

    // GET contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntryDTO> getContactById(@PathVariable int id) {
        if (id >= 0 && id < contacts.size()) {
            AddressBookEntry contact = contacts.get(id);
            return ResponseEntity.ok(new AddressBookEntryDTO(contact.getName(), contact.getPhone()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // POST create a new contact
    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody AddressBookEntryDTO contactDTO) {
        AddressBookEntry newContact = new AddressBookEntry(contactDTO.getName(), contactDTO.getPhone());
        contacts.add(newContact);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact added successfully");
    }

    // PUT update contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable int id, @RequestBody AddressBookEntryDTO updatedContactDTO) {
        if (id >= 0 && id < contacts.size()) {
            AddressBookEntry contact = contacts.get(id);
            contact.setName(updatedContactDTO.getName());
            contact.setPhone(updatedContactDTO.getPhone());
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
