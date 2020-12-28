package ro.tuc.ds2020.services.security;

import java.util.Collections;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.UserEntity;
import ro.tuc.ds2020.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userRepository.getByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User " + username + " was not found in the database");
    }
    return buildUserForAuthentication(user, Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
  }


  private UserDetails buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        user.getActive(), true, true, true, authorities);
  }
}
