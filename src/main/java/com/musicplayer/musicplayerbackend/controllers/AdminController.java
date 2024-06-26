package com.musicplayer.musicplayerbackend.controllers;

import com.musicplayer.musicplayerbackend.Service.UserService;
import com.musicplayer.musicplayerbackend.model.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")//go to localhost:8080/admin/home to see admin interface
    public String adminHome() {
        return "admin";
    }

    @GetMapping("/get_all_users")
    public String getAllUsers(Model model) {
        List<RegularUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/createUser")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new RegularUser());
        return "createUser";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") RegularUser user) {
        userService.createUser(user);
        return "redirect:/admin/get_all_users";
    }

    @GetMapping("/get_user")
    public String getUserByUsername(@RequestParam("username") String username, Model model) {
        Optional<RegularUser> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()) {
            RegularUser user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("userFound", true);
        } else {
            model.addAttribute("userFound", false);
        }
        return "admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUserByUsername(@RequestParam("username") String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/admin/get_all_users";
    }
}
