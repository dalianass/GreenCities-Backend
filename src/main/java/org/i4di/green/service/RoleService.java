package org.i4di.green.service;

import org.i4di.green.domain.Role;
import org.i4di.green.dto.RoleDTO;

import java.util.Optional;

public interface RoleService {
    Optional<RoleDTO> createNewRole(RoleDTO roleDTO);
}
