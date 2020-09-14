package com.zzz.web.login;

import com.zzz.pojo.User;
import com.zzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                        HttpSession httpSession,
                        RedirectAttributes redirectAttributes) {
        User user = userService.checkUser(userName, password);
        if (user != null) {
            user.setPassword(null);
            httpSession.setAttribute("user", user);
            return "admin/index";
        }else {
            redirectAttributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
            httpSession.removeAttribute("user");
            return "redirect:/admin";

    }
}
