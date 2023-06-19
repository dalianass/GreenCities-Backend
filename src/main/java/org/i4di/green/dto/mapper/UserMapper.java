package org.i4di.green.dto.mapper;

import org.i4di.green.domain.Report;
import org.i4di.green.domain.User;
import org.i4di.green.dto.UserDTO;
import org.i4di.green.dto.mapper.defaults.UserFromId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring", uses={RoleMapper.class})
public interface UserMapper  { //da li ovde treba extends RoleFromId?
    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "role", target = "role")


    UserDTO userToUserDTO(User user);
    //koristi informacije iz @Mapping da kreira UserDto

    List<UserDTO> usersToUserDTOs(List<User> users);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "role", target = "role")

    User userDTOToUser(UserDTO userDTO);

    List<User> userDTOsToUsers(List<UserDTO> userDTOs);
}
