package com.sqli.techtuesday.boot.config;

import com.google.common.collect.ImmutableList;
import com.sqli.techtuesday.boot.filter.CustomRandomHeaderFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class FilterConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("classpath:/data/chuck-norris-favorite-facts.txt")
    private Resource chuckNorrisFacts;

    @Bean
    public Filter customRandomHeaderFilter() throws IOException {
        ImmutableList<String> facts;
        if (chuckNorrisFacts.exists()) {
            Path path = chuckNorrisFacts.getFile().toPath();
            facts = Files.lines(path).filter(line -> !line.isEmpty() && !line.startsWith("#")).collect(toImmutableList());
        } else {
            facts = ImmutableList.of();
        }
        logger.info("loaded facts: {}", facts);

        return new CustomRandomHeaderFilter("Chuck-Norris-Fact", facts, new Random());
    }
}
