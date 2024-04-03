package com.app.jjwt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JJWTSecurityFilter extends OncePerRequestFilter {

    private JJWTTokenService jjwtTokenService;

    private JJWTUserDetailsService jjwtUserDetailsService;

    @Autowired
    public JJWTSecurityFilter(JJWTTokenService jjwtTokenService,
                              JJWTUserDetailsService jjwtUserDetailsService){
        this.jjwtTokenService = jjwtTokenService;
        this.jjwtUserDetailsService = jjwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = this.jjwtTokenService.validateToken(token);

        if(login != null){
            var user= this.jjwtUserDetailsService.loadUserByUsername(login);
            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null){
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}