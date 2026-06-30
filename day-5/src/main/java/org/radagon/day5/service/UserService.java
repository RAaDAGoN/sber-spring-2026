package org.radagon.day5.service;

import lombok.AllArgsConstructor;
import org.radagon.day5.dto.BookDTO;
import org.radagon.day5.dto.UserDTO;
import org.radagon.day5.entity.Book;
import org.radagon.day5.entity.User;
import org.radagon.day5.repository.BookRepository;
import org.radagon.day5.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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


        List<Book> books = userDTO.getBooks().stream()
                .map(bookDTO -> Book.builder()
                        .bookTitle(bookDTO.getBookTitle())
                        .bookAuthor(bookDTO.getBookAuthor())
                        .user(user)
                        .build())
                .toList();

        user.setBooks(books);

        User savedUser = userRepository.save(user);

        return savedUser;
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
        if (userDTO != null) {
            User user = findById(userDTO.getId());
            user.setUserName(userDTO.getUserName());
            user.setUserEmail(userDTO.getUserEmail());

            if (userDTO.getBooks() != null) {
                user.getBooks().clear();

                List<Book> newBook = userDTO.getBooks().stream()
                        .map(bookDTO -> Book.builder()
                                .bookTitle(bookDTO.getBookTitle())
                                .bookAuthor(bookDTO.getBookAuthor())
                                .user(user)
                                .build())
                        .toList();

                user.getBooks().addAll(newBook);
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
