package com.example.Surveillance.Security;

import com.example.Surveillance.Entities.user.User;
import com.example.Surveillance.Exception.BadRequestException;
import com.example.Surveillance.Exception.ErrorDetails;
import com.example.Surveillance.Repositories.TokenRepository;
import com.example.Surveillance.Services.UserService;
import com.example.Surveillance.token.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Component
public class AuthenticationFilter implements Filter {

    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationFilter(UserService userService, TokenRepository tokenRepository, JwtUtil jwtUtil) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ("/auth/login".equals(httpRequest.getRequestURI())) {
            // Handle login and generate the ResponseEntity
            ResponseEntity<?> result = handleLogin(httpRequest);

            // Set the HTTP response based on the ResponseEntity
            httpResponse.setStatus(result.getStatusCodeValue());
            httpResponse.setContentType("application/json");

            // Write the response body
            Object responseBody = result.getBody();
            if (responseBody != null) {
                if (responseBody instanceof String) {
                    httpResponse.getWriter().write((String) responseBody);
                } else {
                    httpResponse.getWriter().write(convertObjectToJson(responseBody));
                }
            }
            return;
        }

        chain.doFilter(request, response);
    }

    private ResponseEntity<?> handleLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userService.getUser(username);

            if (user != null && user.getPassword().equals(password)) {
                // Generate JWT token
                String token = jwtUtil.generateToken(username);

                // Check for existing token
                Token existingToken = tokenRepository.findByUser(user);
                if (existingToken != null) {
                    // Update existing token
                    updateToken(existingToken, token);
                } else {
                    // Create new token
                    createNewToken(user, token);
                }

                return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
            } else {
                throw new BadRequestException("Invalid credentials");
            }
        } catch (BadRequestException e) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), "Invalid credentials");
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
    }

    private void updateToken(Token existingToken, String token) {
        existingToken.setToken(token);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        existingToken.setExpirationDate(calendar.getTime());
        existingToken.setValid(true);
        tokenRepository.save(existingToken);
    }

    private void createNewToken(User user, String token) {
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        tokenEntity.setExpirationDate(calendar.getTime());
        tokenEntity.setValid(true);
        tokenEntity.setUser(user);
        tokenRepository.save(tokenEntity);
    }

    //  convert an object to a JSON string
    private String convertObjectToJson(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    @Override
    public void destroy() {}
}

