package com.mvc.web.controller;

import com.mvc.web.entity.Login;
import com.mvc.web.model.LoginDto;
import com.mvc.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/index")
    public String home(@ModelAttribute("login") LoginDto loginDto, BindingResult result, Model model){
            Login existingUser = loginService.findByEmail(loginDto.getEmail());
            if (!result.hasErrors() && existingUser.getPassword().equalsIgnoreCase(loginDto.getPassword())) {
                model.addAttribute("login", loginDto);
                return "index";
            }
//        throw new LoginException("Invalid Email or Invalid password");
        return "redirect:/login?error";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){

        LoginDto loginDto = new LoginDto();
        model.addAttribute("login",loginDto);
        return "register";

    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("login") LoginDto loginDto, BindingResult result, Model model){


        Login existingUser = loginService.findByEmail(loginDto.getEmail());

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email","","there is already an account existed with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("login",loginDto);
            return "/register";
        }

        loginService.saveUser(loginDto);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
