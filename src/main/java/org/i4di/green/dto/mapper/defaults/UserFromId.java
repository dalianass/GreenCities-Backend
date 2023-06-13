package org.i4di.green.dto.mapper.defaults;

import org.i4di.green.domain.User;

public interface UserFromId {
    default User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);

        return user;
    }
}