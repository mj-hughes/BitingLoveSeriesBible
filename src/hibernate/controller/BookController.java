package hibernate.controller;

import hibernate.entity.Book;
import hibernate.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/list")
    public String listBooks(Model theModel) {
        List<Book> bookList = bookService.getBooks();
        theModel.addAttribute("books", bookList);
        return "list-books";
    }

    @GetMapping("/addBookForm")
    public String addBookForm(Model theModel) {
        Book theBook = new Book();
        theModel.addAttribute("book", theBook);
        return "add-book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid
            @ModelAttribute("book") Book theBook,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "add-book-form";
        }
        bookService.saveBook(theBook);
        return "redirect:/book/list";
    }

    @RequestMapping("/updateBookForm")
    public String updateBookForm(
            @RequestParam("bookId") int theId,
            Model theModel) {
        Book theBook = bookService.getBook(theId);
        theModel.addAttribute("book", theBook);
        return "add-book-form";
    }

    @GetMapping("/delete")
    public String deleteBook(
            @RequestParam("bookId") int theId) {
        bookService.deleteBook(theId);
        return "redirect:/book/list";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("searchTerm") String theSearchTerm,
            Model theModel    ) {
        List<Book> matchingBooks =
                bookService.getBooksByName(theSearchTerm);
        theModel.addAttribute("books", matchingBooks);
        return "list-books";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor ste = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, ste);
    }

}
