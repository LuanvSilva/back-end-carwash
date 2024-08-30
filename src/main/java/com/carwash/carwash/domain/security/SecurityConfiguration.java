// package com.carwash.carwash.domain.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;

// @Configuration
// @EnableAutoConfiguration
// public class SecurityConfiguration {

//     @Autowired
//     private SecurityFilter securityFilter;
//     @Bean
//     public SecurityFilter securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         return httpSecurity
//                 .csrf(AbstractHttpConfigurer::disable)
//                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .authorizeHttpRequests(authoriza -> authoriza
//                         .requestMatchers(HttpMethod.GET, "/usuarios/admin").hasRole("ADMIN")
//                         .requestMatchers(HttpMethod.GET, "/usuarios/user").hasRole("USER")
//                         .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
//                         .requestMatchers(HttpMethod.POST, "/auth").permitAll()
//                         .requestMatchers(HttpMethod.POST, "/auth/refresh-token").permitAll()
//                         .anyRequest().authenticated()
//                 )
//                 .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//                 .build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//             throws Exception {
//         return authenticationConfiguration.getAuthenticationManager();
//     }

// }
