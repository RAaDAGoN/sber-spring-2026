package org.radagon.day5.service;

import lombok.AllArgsConstructor;
import org.radagon.day5.dto.BookDTO;
import org.radagon.day5.entity.Book;
import org.radagon.day5.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public Book createBook(BookDTO bookDTO) {

        Book book = Book.builder()
                .bookTitle(bookDTO.getBookTitle())
                .bookAuthor(bookDTO.getBookAuthor())
                .build();

        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book updateBook(BookDTO bookDTO) {
        if (bookDTO.getId() != null) {
            Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
            book.setBookTitle(bookDTO.getBookTitle());
            book.setBookAuthor(bookDTO.getBookAuthor());

            return bookRepository.save(book);
        } else {
            return createBook(bookDTO);
        }
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }
        bookRepository.deleteById(id);
    }
}
