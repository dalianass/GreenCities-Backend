package org.i4di.green.service;

import org.i4di.green.domain.User;
import org.i4di.green.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> list();

    Optional<UserDTO> show(Long id);

    Optional<UserDTO> showByEmail(String email);

//    List<UserDTO> search(String firstName, String lastName);

    Optional<UserDTO> create(UserDTO userDTO);

    boolean delete(Long id);

    Optional<UserDTO> update(Long id, UserDTO userDTO);

    Optional<UserDTO> registerNewUser(UserDTO userDTO);

    List<User> getUsers() throws Exception;

    String getEncodedPassword(String password);
}
