package com.sqli.techtuesday.boot.model;

import java.util.List;
import java.util.UUID;

public interface RecommendationRepository {

    public List<Recommendation> findAll();
}
