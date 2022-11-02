package com.example.springboot.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.model.Book;

public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);

}
