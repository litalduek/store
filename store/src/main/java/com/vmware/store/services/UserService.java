package com.vmware.store.services;

import com.vmware.store.dto.Message;
import com.vmware.store.dto.user.LoginData;
import com.vmware.store.dto.user.SignupData;
import com.vmware.store.model.User;

public interface UserService {
    Message login(LoginData loginData);
    Message signup(SignupData signUp);
    User getUser(String userName);

}
