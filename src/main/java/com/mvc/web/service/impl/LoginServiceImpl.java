package com.mvc.web.service.impl;

import com.mvc.web.entity.Login;
import com.mvc.web.model.LoginDto;
import com.mvc.web.repository.LoginRepository;
import com.mvc.web.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void saveUser(LoginDto loginDto) {
        Login login = new Login();
        login.setName(loginDto.getName());
        login.setEmail(loginDto.getEmail());
        login.setPassword(loginDto.getPassword());

        loginRepository.save(login);
    }

    @Override
    public Login findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }
}
