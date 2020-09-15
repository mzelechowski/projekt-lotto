package com.koncowy.config;

import com.koncowy.authorize.CustomUserService;
import com.koncowy.repository.UserAppRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserAppRepository.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final CustomUserService customUserService;

  public SecurityConfig(PasswordEncoder passwordEncoder, CustomUserService customUserService) {
    this.passwordEncoder = passwordEncoder;
    this.customUserService = customUserService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().disable(); //todo wylaczamy zabezpieczenia przed np. DDOS
    http.csrf().disable(); //todo wylaczamy zabezpieczenia przed np. DDOS

    http
        .authorizeRequests() //wlaczani filtrowania requestów z sieci
        .antMatchers("/login**", "/register**").permitAll() // wylaczenie filtru na te konrektne urle
        .and()
        .formLogin()
        .loginPage("/login") //ustawiamy endpoint do naszego kontrolela widoku gdzie mamy strone logowania
        .loginProcessingUrl("/signin") //miejsce gdzie przychodzi zapytanie z formularza html
        .usernameParameter("username")
        .passwordParameter("password")
//        .successHandler((res, req, auth) -> { //rozszerzona wersja tego co ponizej.
//          req.sendRedirect("/login");
//        })
        //.successForwardUrl("/login") //przekierowanie po udanym zalogowaniu
        .defaultSuccessUrl("/login")
        .failureForwardUrl("/login")
        .permitAll()
        .and()
        .logout() //konfiguracja akcji wylogowania
        .logoutUrl("/logout")
        .permitAll(); //wylaczanie filtrowania dla logout
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(customUserService)
        .passwordEncoder(passwordEncoder);
  }
}
