package com.koncowy.service;

import com.koncowy.model.Role;
import com.koncowy.model.UserApp;
import com.koncowy.repository.RoleRepository;
import com.koncowy.repository.UserAppRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserAppService {
  private UserAppRepository userAppRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;

  private AuthenticationManager authenticationManager;

  public UserAppService(UserAppRepository userAppRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.userAppRepository = userAppRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserApp getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Optional<UserApp> currentUser = userAppRepository.findUserByUsername(auth.getName());
    return currentUser.orElseThrow(NullPointerException::new);
  }

  public String registerNewUser(UserApp userApp) {
    if (userAppRepository.findUserByUsername(userApp.getLogin()).isPresent()) {
      return "ERROR: User" + userApp.getLogin() + " exis!";
    } else {
      userApp.setActive(1);
      HashSet<Role> roles = new HashSet<>();
      roles.add(roleRepository.findById(2L).get());  //tutaj powinno być orElsetGet
      userApp.setRoles(roles);
      String pass = userApp.getPassword();
      userApp.setPassword(passwordEncoder.encode(pass));

      if (userAppRepository.save(userApp) != null) {
        return "Success: user " + userApp.getLogin() + " registered";
      }
    }
    return null;
  }
}
