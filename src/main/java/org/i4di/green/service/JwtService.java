package org.i4di.green.service;
import org.i4di.green.domain.JwtRequest;
import org.i4di.green.domain.JwtResponse;
import org.i4di.green.domain.User;
import org.i4di.green.dto.UserDTO;
import org.i4di.green.dto.mapper.UserMapper;
import org.i4di.green.repository.UserRepository;
import org.i4di.green.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserMapper userMapper;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String email = jwtRequest.getEmail();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(email, userPassword);

        UserDetails userDetails = loadUserByUsername(email);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByEmail(email).get();
        UserDTO userDTO = userMapper.userToUserDTO(user);
        return new JwtResponse(userDTO, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    //nadji uloge
    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String email, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
