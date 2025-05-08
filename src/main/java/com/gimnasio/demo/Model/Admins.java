package com.gimnasio.demo.Model;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
@Component
public class Admins {

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder1;

    @PostConstruct
    public void addAdmins() {

      if(!userDetailsManager.userExists("Bauti")){
          UserDetails userDetails1 = User.builder().username("Bauti")
                  .password(passwordEncoder1.encode("pepepollo774"))
                  .authorities("ADMIN").build();
          userDetailsManager.createUser(userDetails1);
      }
        if(!userDetailsManager.userExists("Gon")){
            UserDetails userDetails2 = User.builder().username("Gon")
                    .password(passwordEncoder1.encode("gonzaMillo91218"))
                    .authorities("ADMIN").build();
            userDetailsManager.createUser(userDetails2);
        }
        if(!userDetailsManager.userExists("BigFa12")){
            UserDetails userDetails3 = User.builder().username("BigFa12")
                    .password(passwordEncoder1.encode("milrayas12"))
                    .authorities("ADMIN").build();
            userDetailsManager.createUser(userDetails3);
        }
        if(!userDetailsManager.userExists("FranTribu")){
            UserDetails userDetails4 = User.builder().username("FranTribu")
                    .password(passwordEncoder1.encode("miMamaLaMasLinda"))
                    .authorities("ADMIN").build();
            userDetailsManager.createUser(userDetails4);
        }
        if(!userDetailsManager.userExists("Lean")){
            UserDetails userDetails5 = User.builder().username("Lean")
                    .password(passwordEncoder1.encode("LeDaCi123"))
                    .authorities("ADMIN").build();

            userDetailsManager.createUser(userDetails5);
        }

    }





}
