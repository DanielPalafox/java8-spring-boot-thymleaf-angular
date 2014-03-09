package com.sqli.techtuesday.boot.config;

import com.sqli.techtuesday.boot.filter.CustomRandomHeaderFilter;
import com.sqli.techtuesday.boot.model.RandomStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.IOException;

@Configuration
class WebFilterConfig {

    @Autowired
    private RandomStringRepository randomStringRepository;

    @Bean
    Filter customRandomHeaderFilter() throws IOException {
        return new CustomRandomHeaderFilter("Chuck-Norris-Fact", randomStringRepository);
    }
}
