package com.sqli.techtuesday.boot.controller;

import com.sqli.techtuesday.boot.model.Client;
import com.sqli.techtuesday.boot.model.JsonClientRepository;
import com.sqli.techtuesday.boot.model.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private JsonClientRepository clientRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Client getClient(@PathVariable String id) {
        return clientRepository.load(UUID.fromString(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> save(@RequestBody @Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(ValidationError.of(bindingResult), HttpStatus.BAD_REQUEST);
        }

        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<Object>(savedClient, HttpStatus.OK);
    }
}
