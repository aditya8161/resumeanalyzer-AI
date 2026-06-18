package com.resumeanalyzer.auth;

import com.resumeanalyzer.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //1.Read Authorization header
        final String authHeader = request.getHeader("Authorization");

        //2.If no token - skip filter
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //3.extract token from authHeader
        final String jwtToken = authHeader.substring(7);

        try{
            //4.extract email token from token
            String email = jwtService.extractEmail(jwtToken);
            log.info("Email in JwtService: {}",email);

            // 5. If email found and user not already authenticated
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails =userRepository.findByEmail(email)
                                .orElseThrow();

                //6.validate token
                if(jwtService.isTokenValid(jwtToken,userDetails)){
                    log.info("Token is Valid in JwtService: {}",jwtToken);
                    // 7. Create auth object and set in SecurityContext
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

                userDetails.getUsername();
            }

            } catch (Exception e) {
            //Invalid Token - Spring Security handle exception
//            throw new RuntimeException(e);
        }

        filterChain.doFilter(request,response);
    }
}
