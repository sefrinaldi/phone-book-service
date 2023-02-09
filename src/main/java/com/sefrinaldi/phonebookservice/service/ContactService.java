package com.sefrinaldi.phonebookservice.service;

import com.sefrinaldi.phonebookservice.dto.ContactRequestDto;
import com.sefrinaldi.phonebookservice.entity.Contact;
import com.sefrinaldi.phonebookservice.repository.ContactRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final MapperFacade mapperFacade;

    public Contact createContact(ContactRequestDto contactRequestDto) {
        Contact contact = mapperFacade.map(contactRequestDto, Contact.class);
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, ContactRequestDto contactRequestDto) {
        Optional<Contact> contact = contactRepository.findById(id);

        contact.ifPresentOrElse(data -> {
            data.setId(id);
            data.setName(Objects.nonNull(contactRequestDto.getName()) ? contactRequestDto.getName() : data.getName());
            data.setPhoneNumber(Objects.nonNull(contactRequestDto.getPhoneNumber()) ? contactRequestDto.getPhoneNumber() : data.getPhoneNumber());
            contactRepository.save(data);
        }, contact::orElseThrow);
        return contact.get();
    }

    public Page<Contact> getAllContact(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElseThrow();
    }

    public void deleteContact(Long contactId) throws NotFoundException {
        Contact contact = contactRepository.findById(contactId).orElseThrow(() -> new NotFoundException(""));
        contactRepository.delete(contact);
    }
}
