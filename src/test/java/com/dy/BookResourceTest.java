package com.dy;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookResourceTest extends JerseyTest {
    private String id1;
    private String id2;

    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        final BookDao bookDao = new BookDao();
        final ResourceConfig rc = new BookApplication(bookDao);

        return rc;
//        return new ResourceConfig().packages("com.dy").register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bind(bookDao).to(BookDao.class);
//            }
//        });
    }

    @Test
    public void testGetBook() {
        Book response = target().path("books").path(id1).request().get(Book.class);
        assertNotNull(response);
    }

    @Test
    public void testGetBooks() {
        Collection<Book> response = target().path("books").request().get(new GenericType<Collection<Book>>() {
        });
        assertEquals(2, response.size());
        assertNotNull(response);
    }

    @Before
    public void setupBooks() {
        id1 = addBook("Author 1", "Title 1", "1111").readEntity(Book.class).getId();
        id2 = addBook("Author 2", "Title 2", "2222").readEntity(Book.class).getId();
    }

    @Test
    public void testAddBook() {
        Response response = addBook("Author 2", "Title 2", "2222");
        Book returnedBook = response.readEntity(Book.class);
        assertEquals("Title 2", returnedBook.getTitle());
        assertEquals("2222", returnedBook.getIsbn());
        assertEquals("Author 2", returnedBook.getAuthor());
    }

    private Response addBook(String author, String title, String isbn) {
        Book b2 = new Book();
        b2.setAuthor(author);
        b2.setTitle(title);
        b2.setIsbn(isbn);
        b2.setPublished(new Date());
        Entity<Book> bookEntity = Entity.entity(b2, MediaType.APPLICATION_JSON);
        Response response = target("books").request().post(bookEntity);
        return response;
    }

}
