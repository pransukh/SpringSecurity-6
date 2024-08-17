package org.let.securityConfig.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class FilterTestEnv extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("x-env").equals("test")){
            System.out.println("Test Env 🔬🧪");
            response.getWriter().write("you are in test env.");
        }
        filterChain.doFilter(request,response);
    }
}
