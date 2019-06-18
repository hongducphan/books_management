package com.ducph.springboot.controller;

import com.ducph.springboot.model.Book;
import com.ducph.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable long id) {
        Book book = bookRepository.findById(id).get();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Book book) {
        bookRepository.save(book);
        return "Create book successful!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        bookRepository.deleteById(id);
        return "Delete successful!";
    }

    @ResponseBody
    @RequestMapping(path = "/actuator")
    public String home(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String host = request.getServerName();

        // Spring Boot >= 2.0.0.M7
        String endpointBasePath = "/actuator";

        StringBuilder sb = new StringBuilder();

        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        String url = "http://" + host + ":8090" + contextPath + endpointBasePath;

        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");

        sb.append("</ul>");

        return sb.toString();
    }

    @RequestMapping(path = "year/{id}", method = RequestMethod.GET)
    public List<Book> findBooksByYear(@PathVariable int id) {
        return bookRepository.findBooksGreaterThanId(id);
    }

    @RequestMapping(path = "/title/{title}", method = RequestMethod.GET)
    public Book findByTitle(@PathVariable String title) {
        return bookRepository.findByTitle(title);
    }

}
