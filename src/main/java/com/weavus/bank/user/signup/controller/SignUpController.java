package com.weavus.bank.user.signup.controller;

import com.weavus.bank.user.signup.entity.SignUpEntity;
import com.weavus.bank.user.signup.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping("/user/signup")
    public String signUp() {
        return "/user/signup";
    }
    @PostMapping("/user/signup")
    public String save(Model model, SignUpEntity entity) {
        boolean exist = signUpService.checkId(entity.getId());

        if(exist) {
            model.addAttribute("msg", "존재하는 아이디입니다.");
            return "/user/signup";
        }
        boolean result = signUpService.signUp(entity);

        if(result){
            model.addAttribute("msg", "회원가입에 성공하였습니다.");
            return "index";
        } else {
            model.addAttribute("msg", "회원가입에 실패하였습니다.");
            return "/user/signup";
        }

    }
}
