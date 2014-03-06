package com.sqli.techtuesday.boot.controller;

import com.sqli.techtuesday.boot.model.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Controller
public class ExampleController {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/example")
    public String example(Model model) {
        model.addAttribute("exampleText", "Welcome to Thymleaf !");
        model.addAttribute("now", new Date());
        model.addAttribute("clients", clientRepository.findAll());

        return "example";
    }

    @RequestMapping("/another/example")
    public String example2(Model model) {
        model.addAttribute("exampleText", "Another Thymleaf page...");

        return "example-2";
    }
}