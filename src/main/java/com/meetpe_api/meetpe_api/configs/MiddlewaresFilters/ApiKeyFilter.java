package com.meetpe_api.meetpe_api.configs.MiddlewaresFilters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetpe_api.meetpe_api.configs.ApiKeyConfig;
import com.meetpe_api.meetpe_api.exceptions.ErrorResponses.CustomErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private ApiKeyConfig apiKeyConfig;
    private final RequestMatcher uriMatcher =
            new AntPathRequestMatcher("/api/some/url", HttpMethod.GET.name());
    public ApiKeyFilter(ApiKeyConfig apiKeyConfig) {
        this.apiKeyConfig = apiKeyConfig;
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String path = request.getServletPath();
        if(!path.startsWith("/app")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println(path);
        String apiKey = request.getHeader("API-KEY");

        if (apiKey != null && apiKey.equals(apiKeyConfig.getApiKey())) {
            System.out.println(apiKey);
            System.out.println(apiKeyConfig.getApiKey());

            filterChain.doFilter(request, response);
            return;

        } else {
            System.out.println("from filter ");

            CustomErrorResponse errorResponse = new CustomErrorResponse("Invalid API key provided");
            String errorJson = new ObjectMapper().writeValueAsString(errorResponse); // Use ObjectMapper for JSON conversion
            response.setContentType("application/json"); // Set content type to JSON
            response.setStatus(401);
            response.getWriter().write(errorJson);
        }
    }

}
