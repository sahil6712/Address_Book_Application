package com.example.addressbookapp.service;

import com.example.addressbookapp.dto.AddressBookEntryDTO;
import com.example.addressbookapp.model.AddressBookEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {
    private List<AddressBookEntry> contacts = new ArrayList<>();

    public List<AddressBookEntryDTO> getAllContacts() {
        return contacts.stream()
                .map(contact -> new AddressBookEntryDTO(contact.getName(), contact.getPhone()))
                .collect(Collectors.toList());
    }

    public AddressBookEntryDTO getContactById(int id) {
        if (id >= 0 && id < contacts.size()) {
            AddressBookEntry contact = contacts.get(id);
            return new AddressBookEntryDTO(contact.getName(), contact.getPhone());
        }
        return null;
    }

    public void createContact(AddressBookEntryDTO contactDTO) {
        contacts.add(new AddressBookEntry(contactDTO.getName(), contactDTO.getPhone()));
    }

    public boolean updateContact(int id, AddressBookEntryDTO updatedContactDTO) {
        if (id >= 0 && id < contacts.size()) {
            AddressBookEntry contact = contacts.get(id);
            contact.setName(updatedContactDTO.getName());
            contact.setPhone(updatedContactDTO.getPhone());
            return true;
        }
        return false;
    }

    public boolean deleteContact(int id) {
        if (id >= 0 && id < contacts.size()) {
            contacts.remove(id);
            return true;
        }
        return false;
    }
}
