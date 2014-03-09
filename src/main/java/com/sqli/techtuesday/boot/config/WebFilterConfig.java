package com.sqli.techtuesday.boot.config;

import com.google.common.collect.ImmutableList;
import com.sqli.techtuesday.boot.filter.CustomRandomHeaderFilter;
import com.sqli.techtuesday.boot.model.RandomStringRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.servlet.Filter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static com.sqli.techtuesday.boot.util.ToImmutableListCollector.toImmutableList;

@Configuration
public class WebFilterConfig {

    @Autowired
    private RandomStringRepository randomStringRepository;

    @Bean
    public Filter customRandomHeaderFilter() throws IOException {
        return new CustomRandomHeaderFilter("Chuck-Norris-Fact", randomStringRepository);
    }
}
