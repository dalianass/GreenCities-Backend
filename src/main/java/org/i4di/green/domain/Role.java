package org.i4di.green.domain;

import org.i4di.green.dto.TimestampedDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role extends Timestamped {

    private String roleName;
    private String roleDescription;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}

