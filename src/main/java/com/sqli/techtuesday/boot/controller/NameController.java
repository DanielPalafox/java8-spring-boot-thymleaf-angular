package com.sqli.techtuesday.boot.controller;

import com.sqli.techtuesday.boot.model.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/names")
public class NameController {

    private final Pattern expectedPrefixPattern = Pattern.compile("[a-zA-z]+");
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/{prefix}")
    public ResponseEntity<Object> namesByPrefix(@PathVariable String prefix) {
        if (!expectedPrefixPattern.matcher(prefix).matches()) {
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

        List<String> firstNames = clientRepository.findAll().stream()
                .filter(c -> c.getFirstName().startsWith(prefix))
                .map(c -> c.getFirstName())
                .collect(Collectors.toList());

        if (firstNames.isEmpty()) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Object>(firstNames, HttpStatus.OK);
    }
}
