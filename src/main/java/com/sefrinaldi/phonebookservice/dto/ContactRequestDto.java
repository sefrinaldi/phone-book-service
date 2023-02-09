package com.sefrinaldi.phonebookservice.dto;

import lombok.Data;

@Data
public class ContactRequestDto {

    private Long id;
    private String name;
    private String phoneNumber;
}
