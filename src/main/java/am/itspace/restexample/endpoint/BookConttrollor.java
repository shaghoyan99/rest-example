package am.itspace.restexample.endpoint;

import am.itspace.restexample.exeption.ResourceNotFoundException;
import am.itspace.restexample.model.Book;
import am.itspace.restexample.repository.BookRepository;
import am.itspace.restexample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookConttrollor {

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exists"));
    }

    @PostMapping("/books")

    public Book crate(@RequestBody Book book) {
        if (book.getId() > 0) {
            throw new RuntimeException("Id must be 0");
        }
        return bookService.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody Book book, @PathVariable("id") int id) {
        Book bookFromDB = bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book does not exists"));
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setDescription(book.getDescription());
        bookFromDB.setPrice(book.getPrice());
        bookFromDB.setAuthorName(book.getAuthorName());
        return bookService.save(bookFromDB);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") int id) {
        bookService.delete(bookService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book does not exists")));
    }


}
