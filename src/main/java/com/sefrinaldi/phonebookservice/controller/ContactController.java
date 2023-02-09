package com.sefrinaldi.phonebookservice.controller;

import com.sefrinaldi.phonebookservice.dto.ContactRequestDto;
import com.sefrinaldi.phonebookservice.entity.Contact;
import com.sefrinaldi.phonebookservice.service.ContactService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact-service")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public Contact createContact(@RequestBody ContactRequestDto contactRequestDto) {
        return contactService.createContact(contactRequestDto);
    }

    @PutMapping("/update/{id}")
    public Contact updateContact(@PathVariable(value = "id") Long id,
                                 @RequestBody ContactRequestDto contactRequestDto) {
        return contactService.updateContact(id, contactRequestDto);
    }

    @GetMapping("/contact-detail")
    public Page<Contact> getAllContact(Pageable pageable) {
        return contactService.getAllContact(pageable);
    }

    @GetMapping("/contact-detail/{id}")
    public Contact getContactById(@PathVariable(value = "id") Long id) {
        return contactService.getContactById(id);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable(value = "id") Long contactId) throws NotFoundException {
        contactService.deleteContact(contactId);
        return ResponseEntity.ok("deleted");
    }
}
