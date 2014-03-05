package com.sqli.techtuesday.boot.filter;

import com.google.common.collect.ImmutableList;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public class CustomRandomHeaderFilter extends OncePerRequestFilter {

    private final String headerName;
    private final List<String> headerPossibleValues;
    private final int numberOfPossibleValues;
    private final Random randomIntGenerator = new Random();

    public CustomRandomHeaderFilter(String headerName, ImmutableList<String> headerPossibleValues) {
        this.headerName = checkNotNull(headerName);
        this.headerPossibleValues = checkNotNull(headerPossibleValues);
        this.numberOfPossibleValues = headerPossibleValues.size();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String value = headerPossibleValues.get(randomIntGenerator.nextInt(numberOfPossibleValues));
        httpServletResponse.addHeader(headerName, value);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
