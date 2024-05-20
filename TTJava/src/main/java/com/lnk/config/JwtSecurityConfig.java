/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lnk.config;

import com.lnk.filters.CustomAccessDeniedHandler;
import com.lnk.filters.JwtAuthenticationTokenFilter;
import com.lnk.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author ADMIN
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.lnk.controllers",
    "com.lnk.repository",
    "com.lnk.service",
    "com.lnk.components"})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().ignoringAntMatchers("/api/**"); 
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/foods/").permitAll();
        http.authorizeRequests().antMatchers("/api/foods/**").permitAll();
        http.authorizeRequests().antMatchers("/api/createFoods/").permitAll();
        http.authorizeRequests().antMatchers("/api/stores/**").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/usersStore/").permitAll();
        http.authorizeRequests().antMatchers("/api/createStores/").permitAll();
        http.authorizeRequests().antMatchers("/api/updateUsers/").permitAll();
        http.authorizeRequests().antMatchers("/api/order/").permitAll();
        http.authorizeRequests().antMatchers("/api/order/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/reviews/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/foods/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/orderdetails/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/**/order/").permitAll();
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_STORE') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_STORE') or hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_STORE') or hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
