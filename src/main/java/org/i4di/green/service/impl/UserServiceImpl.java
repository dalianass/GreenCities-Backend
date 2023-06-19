package org.i4di.green.service.impl;
import org.i4di.green.domain.Role;
import org.i4di.green.domain.User;
import org.i4di.green.dto.UserDTO;
import org.i4di.green.dto.mapper.UserMapper;
import org.i4di.green.repository.RoleRepository;
import org.i4di.green.repository.UserRepository;
import org.i4di.green.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> list() {
        return userMapper.usersToUserDTOs(userRepository.findAllByOrderByFirstNameAsc());
    }

    @Override
    public Optional<UserDTO> show(Long id) {
        Optional<User> byId = userRepository.findById(id);

        return byId.map(userMapper::userToUserDTO);
    }

    @Override
    public Optional<UserDTO> showByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);

        return byEmail.map(userMapper::userToUserDTO);
    }


    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        User toCreate = userMapper.userDTOToUser(userDTO);

        return Optional.of(userMapper.userToUserDTO(userRepository.save(toCreate)));
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> byId = userRepository.findById(id);

        if (!byId.isPresent() || byId.get().getDeleted()) {
            return false;
        }

        userRepository.delete(id);
        return true;
    }

    @Override
    public Optional<UserDTO> update(Long id, UserDTO userDTO) {
        Optional<User> byId = userRepository.findById(id);

        if (!byId.isPresent()) {
            return Optional.empty();
        } else {
            byId.get().setFirstName(userDTO.getFirstName());
            byId.get().setLastName(userDTO.getLastName());
            byId.get().setEmail(userDTO.getEmail());

            return Optional.of(userMapper.userToUserDTO(
                    userRepository.save(byId.get())
            ));
        }
    }

    @Override
    public Optional<UserDTO>  registerNewUser(UserDTO userDTO){
        Optional<User> userAlreadyExists = userRepository.findByEmail(userDTO.getEmail());
        if(!userAlreadyExists.isPresent()) {
            User toCreate = userMapper.userDTOToUser(userDTO);

            Role role = roleRepository.findById(2L).get(); //2 je id za korisnika
            Set<Role> userRoles = new HashSet<>(); //set moze sadrzati samo unikate
            userRoles.add(role);
            toCreate.setRole(userRoles);
            toCreate.setPassword(getEncodedPassword(userDTO.getPassword()));
            return  Optional.of(userMapper.userToUserDTO(userRepository.save(toCreate)));
        }
        else {
            return Optional.empty();
        }
    }
    @Override
    public List<User> getUsers() throws Exception{
        if(userRepository.count() > 0) {
            return (List<User>) userRepository.findAll();
        }
        else {
            throw new Exception("Ne postoji nijedan user u bazi");
        }
    }

    @Override
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}