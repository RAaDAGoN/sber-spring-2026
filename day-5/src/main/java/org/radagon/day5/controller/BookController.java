package org.radagon.day5.controller;

import lombok.AllArgsConstructor;
import org.radagon.day5.dto.BookDTO;
import org.radagon.day5.entity.Book;
import org.radagon.day5.entity.User;
import org.radagon.day5.repository.BookRepository;
import org.radagon.day5.service.BookService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;



    /*
    Отдаёт список всех книг из бд
     */
    @GetMapping
    public String booksPage(@AuthenticationPrincipal User currentUser, Model model) {
        List<Book> books;

        if (currentUser.getRole().equals("ROLE_ADMIN")) {
            books = bookRepository.findAll();
        } else {
            books = bookRepository.findByUserId(currentUser.getId());
        }

        model.addAttribute("books", books);

        return "books/Books";
    }

    /*
    Позволяет создать пустую html страницу (форму)
    для создания новой книги
     */
    @GetMapping("/new")
    public String CreateBook(Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("book", bookDTO);

        return "books/BooksForm";
    }

    /*
    Отдает html страницу (форму) заполненную данными
     */
    @GetMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setBookTitle(book.getBookTitle());
        bookDTO.setBookAuthor(book.getBookAuthor());

        model.addAttribute("book", bookDTO);
        return "books/BooksForm";
    }

    /*
    Отлавливает 2 действия: создать или обновить
    для @GetMapping("/new")
    и @GetMapping("/edit/{id}")
     */
    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute BookDTO bookDTO, @AuthenticationPrincipal User currentUser) {
        bookDTO.setUserId(currentUser.getId());
        bookService.updateBook(bookDTO);
        return "redirect:/books";
    }

    /*
    Отлавливает делит действия
    обработка на исключения при делит допустима, но
    не имеет смысла, т.к. @PathVariable не позволит
    id прийти null
     */
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
