package com.development.api.SevaSahyog.auth.config;

import com.development.api.SevaSahyog.auth.service.JWTService;
import com.development.api.SevaSahyog.auth.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;    // Service for handling JWT operations

    private final UserService userService;  // Service for handling user-related operations

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extracting the Authorization header from the request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // if auth header is present and starts with "Bearer "
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);    // If not, proceed with the next filter
            return;
        }

        // Extract the JWT from the Authorization header
        jwt = authHeader.substring(7);
        // Extract the username (email) from the JWT
        userEmail = jwtService.extractUserName(jwt);

        // Check if the username is not empty and the user is not authenticated yet
        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details using the username
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

            // Validate the token with the user details
            if (jwtService.isTokenValid(jwt, userDetails)){
                // Create an empty security context
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                // Create an authentication token
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                // Set additional details for the authentication token
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication token in the security context
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);  // Set the security context to the holder
            }
        }

//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            // Extract the JWT token from the Authorization header
//            String jwtToken = authHeader.substring(7);
//
//            // Extracting user email from the JWT token
//            String userEmail = jwtService.extractUserName(jwtToken);
//
//            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                // Load user details using the user email
//                UserDetails userDetails = jwtService.loadUserByUsername(userEmail);
//
//                if (jwtService.isTokenValid(jwtToken, userDetails)) {
//                    // Create authentication token
//                    UsernamePasswordAuthenticationToken authenticationToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    // Set the authentication token in the SecurityContext
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//        }

        // Proceed with the next filter
        filterChain.doFilter(request, response);
    }
}
