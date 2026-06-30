package org.radagon.day5.controller;

import lombok.AllArgsConstructor;
import org.radagon.day5.dto.UserDTO;
import org.radagon.day5.entity.User;
import org.radagon.day5.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String usersPage(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "users/Users";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        UserDTO userDTO = new UserDTO();

        model.addAttribute("userDTO", userDTO);

        return "users/UserForm";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());




//        userDTO.setBooks();

        return "users/UserForm";
    }
}
