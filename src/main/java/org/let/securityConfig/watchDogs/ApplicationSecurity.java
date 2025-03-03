package org.let.securityConfig.watchDogs;

import org.let.securityConfig.authenticators.authenticationProviders.DbUserAuthProvider;
import org.let.securityConfig.filters.FilterTestEnv;
import org.let.services.ServiceUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.header.writers.ContentSecurityPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity {

    @Autowired
    ServiceUserDetails serviceUserDetails;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        return httpSecurity.authorizeHttpRequests(authorizeHttp ->{
            authorizeHttp.requestMatchers("/api/menu/**").permitAll()
                    .requestMatchers("/api/public/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated();
        }).formLogin(l-> l.defaultSuccessUrl("/api/home"))
                .logout(l->l.logoutSuccessUrl("/"))
//                .addFilterAfter(new FilterTestEnv(), AuthorizationFilter.class) // Authorization filter does the above url matching
                .authenticationProvider(new DbUserAuthProvider(serviceUserDetails))
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())  // Same-origin iframe embedding
                        .addHeaderWriter(new ContentSecurityPolicyHeaderWriter("frame-ancestors 'self';")) // Adds CSP header to control framing
                ).build();
    }
}
