package com.sefrinaldi.phonebookservice.repository;

import com.sefrinaldi.phonebookservice.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
