package com.example.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Book;
import com.example.springboot.repo.BookRepo;

@Service
public class BookService {
	
    private static Logger log = LogManager.getLogger(BookService.class);
//    private static Logger log = LogManager.getLogger(Application.class);

	
	@Autowired
    private BookRepo bookRepo;
	
	public void saveBook(String bookName) {
		log.info("Saving Book: {}", bookName);
		List<Book> lbook = bookRepo.findByName(bookName);
		if (lbook.size() > 0) {
			log.info("Book: {}, already exist.", bookName);
		} else {
			bookRepo.save(new Book(bookName));
		}
	}
	
	public String getAllBook() {
		log.info("Get All Book: ");
		List<Book> lbook = (List<Book>) bookRepo.findAll();
		for (Book book : lbook) {
			log.info(book.getName());
		}
		return "";
	}
	

	
	
}
