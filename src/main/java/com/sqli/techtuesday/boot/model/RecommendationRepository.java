package com.sqli.techtuesday.boot.model;

import java.util.List;

public interface RecommendationRepository {

    public List<Recommendation> findAll();
}
