package com.dy;

import java.util.*;

public class BookDao {

    private Map<String, Book> books;

    public BookDao() {

        books = new HashMap<>();

//        Book b1 = new Book();
//        b1.setAuthor("Author 1");
//        b1.setId("1");
//        b1.setTitle("Title 1");
//        b1.setIsbn("1111");
//        b1.setPublished(new Date());
//
//        Book b2 = new Book();
//        b2.setAuthor("Author 2");
//        b2.setId("2");
//        b2.setTitle("Title 2");
//        b2.setIsbn("2222");
//        b2.setPublished(new Date());

//        books.put(b1.getId(), b1);
//        books.put(b2.getId(), b2);

    }

    Collection<Book> getBooks() {
        return books.values();
    }

    Book getBook(String id) {
        return books.get(id);
    }

    Book addBook(Book newBook) {
        newBook.setId(UUID.randomUUID().toString());
        books.put(newBook.getId(), newBook);
        return newBook;
    }
}
