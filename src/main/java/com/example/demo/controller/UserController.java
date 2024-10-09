package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String findAllUsers(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping("/create")
    public String createUserFrom(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    // Новый метод для редактирования пользователя
    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") UUID id, Model model) {
        User existingUser = userService.findById(id);
        model.addAttribute("user", existingUser);
        return "user-update";  // Возвращаем форму редактирования
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") UUID id, @ModelAttribute User user) {
        userService.update(id, user);
        return "redirect:/users";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
