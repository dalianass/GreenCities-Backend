package org.i4di.green.dto;

import org.i4di.green.dto.validation.EmailUnique;
import org.i4di.green.dto.validation.Required;

import javax.persistence.Column;
import java.util.Set;

public class UserDTO extends TimestampedDTO{
    @Required
    private String firstName;
    @Required
    private String lastName;
    @Required
    @EmailUnique
    private String email;
    @Required
    private String password;
    private Boolean deleted;
    private Set<RoleDTO> role;

    public Set<RoleDTO> getRole() {
        return role;
    }

    public void setRole(Set<RoleDTO> role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        if (deleted == null) {
            deleted = false;
        }
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
}
