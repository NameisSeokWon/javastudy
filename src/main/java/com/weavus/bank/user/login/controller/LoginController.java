package com.weavus.bank.user.login.controller;

import com.weavus.bank.dao.TransInfoDao;
import com.weavus.bank.dao.UserInfoDao;
import com.weavus.bank.dto.UserInfo;
import com.weavus.bank.user.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class LoginController {

    @Autowired
    public UserInfoDao userInfoDao;

    @Autowired
    public TransInfoDao transInfoDao;

    @Autowired
    public LoginService loginService;


    @GetMapping("/user/login")
    public String login() {

        return "/user/login";
    }
    @PostMapping("/user/login")
    public String login1(String id, String password, Model model, HttpServletRequest request) {

        UserInfo userInfo = loginService.login(id, password);

        if(userInfo == null) {
            model.addAttribute("msg", "아이디 또는 패스워드를 입력하세요");
            return "index";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("id", userInfo.getId());
            model.addAttribute("name", userInfo.getName());
            model.addAttribute("userInfo",userInfo);
            return "/function/main";
        }
    }
    @PostMapping("/user/updateUserInfo")
    public String updateUserInfo(String id, String password, String name, String gender, Model model) {
        UserInfo updatedUserInfo = loginService.updateUserInfo(id, password, name, gender);
        if (updatedUserInfo != null) {
            model.addAttribute("name", updatedUserInfo.getName());
            model.addAttribute("userInfo", updatedUserInfo);
        } else {
            model.addAttribute("msg", "사용자 정보를 업데이트할 수 없습니다.");
        }
        return "/function/main";
    }
}