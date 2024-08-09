package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printWelcome(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add_update";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUpdateUser(user);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "add_update";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
