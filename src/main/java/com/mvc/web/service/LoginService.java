package com.mvc.web.service;

import com.mvc.web.entity.Login;
import com.mvc.web.model.LoginDto;

public interface LoginService {

    void saveUser(LoginDto loginDto);

    Login findByEmail(String email);
}
