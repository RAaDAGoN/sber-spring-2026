package org.radagon.day5.controller;

import lombok.AllArgsConstructor;
import org.radagon.day5.dto.BookDTO;
import org.radagon.day5.dto.UserDTO;
import org.radagon.day5.entity.User;
import org.radagon.day5.service.BookService;
import org.radagon.day5.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final BookService bookService;

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
        model.addAttribute("books", bookService.findAllBooks());

        return "users/UserForm";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());

        if (user.getBooks() != null) {
            List<BookDTO> books = user.getBooks().stream()
                    .map(book -> {
                        BookDTO bookDTO = new BookDTO();
                        bookDTO.setId(book.getId());
                        bookDTO.setBookTitle(book.getBookTitle());
                        bookDTO.setBookAuthor(book.getBookAuthor());
                        return bookDTO;
                    })
                    .collect(Collectors.toList());

            userDTO.setBooks(books);
        }

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("books", bookService.findAllBooks());

        return "users/UserForm";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute UserDTO userDTO) {
        userService.update(userDTO);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
