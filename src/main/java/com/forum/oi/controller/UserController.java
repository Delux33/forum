package com.forum.oi.controller;

import com.forum.oi.domain.Role;
import com.forum.oi.domain.User;
import com.forum.oi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(
            @PathVariable User user,
            Model model) {

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        if (user.isAdmin()) {
            userService.saveUser(user, form);

            return "redirect:/user/" + user.getId();
        } else {
            if (!StringUtils.hasText(username)) {
                return "redirect:/user/";
            } else {
                userService.saveUser(user, username, form);
            }
            return "redirect:/user/";
        }
    }
}
