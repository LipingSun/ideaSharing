package edu.sjsu.cmpe275.project.service;

import edu.sjsu.cmpe275.project.domain.User;
import edu.sjsu.cmpe275.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Liping on 12/8/15.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        log.debug("Authenticating {}", username);
        Optional<User> userFromDatabase = userRepository.findByUsername(username);
        return userFromDatabase.map(user -> {
            return new org.springframework.security.core.userdetails.User(username,
                    user.getPassword(), null);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the " +
                "database"));
    }
}

