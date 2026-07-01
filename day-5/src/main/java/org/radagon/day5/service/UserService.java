package org.radagon.day5.service;

import lombok.AllArgsConstructor;
import org.radagon.day5.dto.BookDTO;
import org.radagon.day5.dto.UserDTO;
import org.radagon.day5.entity.Book;
import org.radagon.day5.entity.User;
import org.radagon.day5.repository.BookRepository;
import org.radagon.day5.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public User createUser(UserDTO userDTO) {
        User user = User.builder()
                .userName(userDTO.getUserName())
                .userEmail(userDTO.getUserEmail())
                .build();


        if (userDTO.getBooks() != null && !userDTO.getBooks().isEmpty()) {
            List<Long> bookIds = userDTO.getBooks().stream()
                    .map(BookDTO::getId)
                    .filter(Objects::nonNull)
                    .toList();

            List<Book> books = bookRepository.findAllById(bookIds);

            books.forEach(book -> book.setUser(user));
            user.setBooks(books);
        } else {
            user.setBooks(new ArrayList<>());
        }

        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(UserDTO userDTO) {
        if (userDTO.getId() != null) {
            User user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            user.setUserName(userDTO.getUserName());
            user.setUserEmail(userDTO.getUserEmail());

            user.getBooks().clear();
            if (userDTO.getBooks() != null && !userDTO.getBooks().isEmpty()) {

                List<Long> bookIds = userDTO.getBooks().stream()
                        .map(BookDTO::getId)
                        .filter(Objects::nonNull)
                        .toList();

                List<Book> books = bookRepository.findAllById(bookIds);

                books.forEach(book -> book.setUser(user));
                user.getBooks().addAll(books);
            }

            return userRepository.save(user);
        } else {
            return createUser(userDTO);
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        userRepository.deleteById(id);
    }
}
