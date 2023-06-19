package org.i4di.green.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.i4di.green.domain.Role;
import org.i4di.green.dto.RoleDTO;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-19T15:39:07+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setRoleName( role.getRoleName() );
        if ( role.getCreatedAt() != null ) {
            roleDTO.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( role.getCreatedAt() ).trim() );
        }
        roleDTO.setId( role.getId() );
        roleDTO.setRoleDescription( role.getRoleDescription() );
        if ( role.getUpdatedAt() != null ) {
            roleDTO.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).print( role.getUpdatedAt() ).trim() );
        }

        return roleDTO;
    }

    @Override
    public List<RoleDTO> rolesToRoleDTOs(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( roles.size() );
        for ( Role role : roles ) {
            list.add( roleToRoleDTO( role ) );
        }

        return list;
    }

    @Override
    public Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleName( roleDTO.getRoleName() );
        if ( roleDTO.getCreatedAt() != null ) {
            role.setCreatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( roleDTO.getCreatedAt() ) );
        }
        role.setId( roleDTO.getId() );
        role.setRoleDescription( roleDTO.getRoleDescription() );
        if ( roleDTO.getUpdatedAt() != null ) {
            role.setUpdatedAt( DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ssZZ" ).parseDateTime( roleDTO.getUpdatedAt() ) );
        }

        return role;
    }

    @Override
    public List<Role> roleDTOsToRoles(List<RoleDTO> rolesDTO) {
        if ( rolesDTO == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( rolesDTO.size() );
        for ( RoleDTO roleDTO : rolesDTO ) {
            list.add( roleDTOToRole( roleDTO ) );
        }

        return list;
    }
}
