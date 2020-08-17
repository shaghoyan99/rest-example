package am.itspace.restexample.service;

import am.itspace.restexample.model.Book;
import am.itspace.restexample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;

    public Book save(Book book) {
        bookRepo.save(book);
        return book;
    }

    public Optional<Book> findById(int id) {
        return bookRepo.findById(id);
    }

    public Book getOne(int id) {
        return bookRepo.getOne(id);
    }

    public List<Book> findAll() {
        return  bookRepo.findAll();
    }

    public void deleteById(int id) {
        bookRepo.deleteById(id);
    }

    public void delete(Book book) {
        bookRepo.delete(book);
    }
}
