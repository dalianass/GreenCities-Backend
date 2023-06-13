package org.i4di.green.domain;

import org.i4di.green.dto.UserDTO;

public class JwtResponse {

    private UserDTO user;
    private String jwtToken;

    public JwtResponse(UserDTO user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
