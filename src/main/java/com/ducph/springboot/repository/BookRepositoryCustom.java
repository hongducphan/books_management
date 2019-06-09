package com.ducph.springboot.repository;

import com.ducph.springboot.model.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findBooksGreaterThanId(int id);
}
