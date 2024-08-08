package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.DAO.User_DAO;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String printWelcome(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add_update";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUpdateUser(user);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateUser(@RequestParam("id") int id, Model model) {
        System.out.println("123");
        model.addAttribute("user", userService.getUser(id));
        return "add_update";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
