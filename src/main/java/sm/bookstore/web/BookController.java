package sm.bookstore.web;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sm.bookstore.domain.Book;
import sm.bookstore.domain.BookRepository;
import sm.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }


    @RequestMapping(value="/booklist", method = RequestMethod.GET)
    public String bookList(Model model){
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("category", categoryRepository.findAll());
        return "booklist";
    }

    // Restful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }
    
    // Restful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
        return bookRepository.findById(bookId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("bookId") Long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @RequestMapping(value ="/add")
    public String addBook(Model model){

            model.addAttribute("book", new Book());
            model.addAttribute("category", categoryRepository.findAll());
            return "addbook";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {

        bookRepository.save(book);

        return "redirect:/booklist";
    }

    @RequestMapping(value = "/edit/{bookId}")
    public String editBook(@PathVariable("bookId") Long bookId, Model model){
        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("category", categoryRepository.findAll());
        return "editbook";
    }

}
