package hibernate.service.converter;

import hibernate.entity.Book;
import hibernate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToBookConverter implements Converter<String, Book> {
    @Autowired
    private BookService bookService;

    @Override
    public Book convert(String source) {
        int bookId = Integer.parseInt(source);
        Book aBook = bookService.getBook(bookId);
        return aBook;
    }
}
