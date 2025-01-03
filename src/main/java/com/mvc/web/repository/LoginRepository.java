package com.mvc.web.repository;

import com.mvc.web.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,Long> {

    Login findByEmail(String email);
}
