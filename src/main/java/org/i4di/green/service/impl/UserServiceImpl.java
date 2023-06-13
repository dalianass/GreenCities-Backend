package org.i4di.green.service.impl;
import org.i4di.green.domain.Role;
import org.i4di.green.domain.User;
import org.i4di.green.dto.UserDTO;
import org.i4di.green.dto.mapper.UserMapper;
import org.i4di.green.repository.RoleRepository;
import org.i4di.green.repository.UserRepository;
import org.i4di.green.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

//    @Autowired
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

    //*************************************DODATO ZBOG JWT
//    @Override
//    public void initRoleAndUser() {
//
//        //kao SEED baze sa ulogama
//        Role adminRole = new Role();
//        adminRole.setRoleName("Admin");
//        adminRole.setRoleDescription("Admin uloga");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setRoleName("User");
//        userRole.setRoleDescription("Podrazumevana uloga za nove korisnike");
//        roleRepository.save(userRole);
//
//        Role radnikRole = new Role();
//        radnikRole.setRoleName("Radnik");
//        radnikRole.setRoleDescription("Uloga za radnike u komunalnim preduzecima");
//        roleRepository.save(radnikRole);
//
//        User adminUser = new User();
//        adminUser.setFirstName("Admin");
//        adminUser.setEmail("admin@gmail.com");
//        adminUser.setPassword(getEncodedPassword("admin@pass"));
//        adminUser.setLastName("Admin");
//        adminUser.setDeleted(false);
//
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRole);
//        adminUser.setRole(adminRoles);
//        userRepository.save(adminUser);
//
//    }

//    @Override
//    public User registerNewUser(User user) {
//        Role role = roleRepository.findById(14L).get(); //dobije role i description
//        Set<Role> userRoles = new HashSet<>(); //set moze sadrzati samo unikate
//        userRoles.add(role);
//        user.setRole(userRoles);
//        user.setPassword(getEncodedPassword(user.getPassword()));
//
//        return userRepository.save(user);
//    }

    @Override
    public Optional<UserDTO>  registerNewUser(UserDTO userDTO) {
        User toCreate = userMapper.userDTOToUser(userDTO);

        Role role = roleRepository.findById(2L).get(); //2 je id za korisnika
        Set<Role> userRoles = new HashSet<>(); //set moze sadrzati samo unikate
        userRoles.add(role);
        toCreate.setRole(userRoles);
        toCreate.setPassword(getEncodedPassword(userDTO.getPassword()));

        return  Optional.of(userMapper.userToUserDTO(userRepository.save(toCreate)));
    }


    //me
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
    //encode je inbuilt. postoji i matches i slicno
}