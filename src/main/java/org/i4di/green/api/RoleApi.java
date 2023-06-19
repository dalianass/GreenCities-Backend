package org.i4di.green.api;

import org.i4di.green.domain.Role;
import org.i4di.green.dto.RoleDTO;
import org.i4di.green.dto.UserDTO;
import org.i4di.green.service.ReportService;
import org.i4di.green.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleApi {

    private RoleService roleService;

    @Autowired
    public RoleApi(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping({"/createNewRole"})
    public ResponseEntity<?> createNewRole(@RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<>(roleService.createNewRole(roleDTO), HttpStatus.CREATED) ;
    }
}
