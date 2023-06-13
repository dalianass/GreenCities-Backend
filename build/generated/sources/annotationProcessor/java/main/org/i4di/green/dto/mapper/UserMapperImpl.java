package org.i4di.green.dto.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.i4di.green.domain.Role;
import org.i4di.green.domain.User;
import org.i4di.green.dto.RoleDTO;
import org.i4di.green.dto.UserDTO;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-12T16:43:17+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        if ( user.getCreatedAt() != null ) {
            userDTO.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( user.getCreatedAt() ).trim() );
        }
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setDeleted( user.getDeleted() );
        userDTO.setRole( roleSetToRoleDTOSet( user.getRole() ) );
        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        if ( user.getUpdatedAt() != null ) {
            userDTO.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( user.getUpdatedAt() ).trim() );
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> usersToUserDTOs(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( userToUserDTO( user ) );
        }

        return list;
    }

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        if ( userDTO.getCreatedAt() != null ) {
            user.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( userDTO.getCreatedAt() ) );
        }
        user.setFirstName( userDTO.getFirstName() );
        user.setLastName( userDTO.getLastName() );
        user.setPassword( userDTO.getPassword() );
        user.setDeleted( userDTO.getDeleted() );
        user.setRole( roleDTOSetToRoleSet( userDTO.getRole() ) );
        user.setId( userDTO.getId() );
        user.setEmail( userDTO.getEmail() );
        if ( userDTO.getUpdatedAt() != null ) {
            user.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( userDTO.getUpdatedAt() ) );
        }

        return user;
    }

    @Override
    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        if ( userDTOs == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDTOs.size() );
        for ( UserDTO userDTO : userDTOs ) {
            list.add( userDTOToUser( userDTO ) );
        }

        return list;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = new HashSet<RoleDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleMapper.roleToRoleDTO( role ) );
        }

        return set1;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleMapper.roleDTOToRole( roleDTO ) );
        }

        return set1;
    }
}
