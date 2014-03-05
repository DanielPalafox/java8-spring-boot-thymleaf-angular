package com.sqli.techtuesday.boot.controller;

import com.sqli.techtuesday.boot.model.Client;
import com.sqli.techtuesday.boot.model.JsonClientRepository;
import com.sqli.techtuesday.boot.model.Recommendation;
import com.sqli.techtuesday.boot.model.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/recommandations")
public class RecommendationController {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Recommendation> getClients() {
        return recommendationRepository.findAll();
    }
}
