package org.example.project3.Configuration;

import lombok.RequiredArgsConstructor;
import org.example.project3.Service.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ConfigSecurity {

    private final UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;

    }

    ////////////////////////

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{

        httpSecurity.csrf().disable()          //Disable the CSRF attack
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  //Session
                .and()
                .authenticationProvider(daoAuthenticationProvider()) //pass the method
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/bank-system/customer/customer-register", "api/v1/bank-system/employee/employee-register").permitAll()
                .requestMatchers("/api/v1/bank-system/account/create-account", "/api/v1/bank-system/account/view/account-details","/api/v1/bank-system/account/delete/account/", "/api/v1/bank-system/account/deposit/", "/api/v1/bank-system/account/withdraw/", "/api/v1/bank-system/customer/update/customer/", "api/v1/bank-system/customer/delete/").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/bank-system/account/active-account/", "/api/v1/bank-system/account/view/users-accounts", "/api/v1/bank-system/account/block-account/", "/api/v1/bank-system/employee/get/all-employees","/api/v1/bank-system/customer/get/all-customers","/api/v1/bank-system/user/get/all-users","/api/v1/bank-system/user/get/user-byId/").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/bank-system/employee/update/employee/", "api/v1/bank-system/employee/delete/employee").hasAuthority("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v2/logout") //logout
                .deleteCookies("JSESSIONID") //now from Postman Cookie
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();

    } //End


}













