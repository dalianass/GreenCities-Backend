package org.i4di.green.dto.mapper;

import org.i4di.green.domain.Report;
import org.i4di.green.domain.Role;
import org.i4di.green.domain.User;
import org.i4di.green.dto.RoleDTO;
import org.i4di.green.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "roleName", target = "roleName")
    @Mapping(source = "roleDescription", target = "roleDescription")

    RoleDTO roleToRoleDTO(Role role);
    List<RoleDTO> rolesToRoleDTOs(List<Role> roles);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Report.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "roleName", target = "roleName")
    @Mapping(source = "roleDescription", target = "roleDescription")

    Role roleDTOToRole(RoleDTO roleDTO);
    List<Role> roleDTOsToRoles(List<RoleDTO> rolesDTO);


}
