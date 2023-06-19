package org.i4di.green.service.impl;


import org.i4di.green.domain.Role;
import org.i4di.green.dto.RoleDTO;
import org.i4di.green.dto.mapper.RoleMapper;
import org.i4di.green.repository.RoleRepository;
import org.i4di.green.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Optional<RoleDTO> createNewRole(RoleDTO roleDTO) {
        Role role = roleMapper.roleDTOToRole(roleDTO);
        return Optional.of(roleMapper.roleToRoleDTO(roleRepository.save(role)));


    }
}
