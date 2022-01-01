package com.onlinestore.services;

import com.onlinestore.dto.Message;
import com.onlinestore.dto.user.LoginData;
import com.onlinestore.dto.user.SignupData;
import com.onlinestore.model.User;

public interface UserService {
    Message login(LoginData loginData);
    Message signup(SignupData signUp);
    User getUser(String userName);

}
