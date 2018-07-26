package com.thoughtworks.nho.util;


import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.domain.User;

public class UserFactory {
    public static JWTUser fromUser(User user) {
        JWTUser jwtUser = new JWTUser();
        jwtUser.setUsername(user.getName());
        return jwtUser;
    }


}
