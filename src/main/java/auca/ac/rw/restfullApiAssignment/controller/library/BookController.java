package auca.ac.rw.restfullApiAssignment.controller.library;

import auca.ac.rw.restfullApiAssignment.modal.library.book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<book> books = new ArrayList<>();
    private Long nextId = 1L;

    public BookController() {
        books.add(new book(nextId++, "Clean Code", "Robert Martin", "978-0132350884", 2008));
        books.add(new book(nextId++, "Effective Java", "Joshua Bloch", "978-0134685991", 2017));
        books.add(new book(nextId++, "Spring in Action", "Craig Walls", "978-1617294945", 2018));
    }

    @GetMapping
    public ResponseEntity<List<book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<book> getBookById(@PathVariable Long id) {
        Optional<book> book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
        
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<book>> searchBooksByTitle(@RequestParam String title) {
        List<book> result = books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<book> addBook(@RequestBody book book) {
        book.setId(nextId++);
        books.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
