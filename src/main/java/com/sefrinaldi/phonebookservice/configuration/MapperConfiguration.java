package com.sefrinaldi.phonebookservice.configuration;

import ma.glasnost.orika.MapperFactory;

public interface MapperConfiguration {
    void configure(MapperFactory mapperFactory);
}
