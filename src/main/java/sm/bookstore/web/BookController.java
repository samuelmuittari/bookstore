package sm.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sm.bookstore.domain.Book;
import sm.bookstore.domain.BookRepository;

@Controller
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value="/booklist", method = RequestMethod.GET)
    public ModelAndView bookList(){
        List<Book> books = (List<Book>) bookRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("booklist");
            modelAndView.addObject("books", books);
            return modelAndView;
    }
    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("bookId") Long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }
    @RequestMapping(value ="/add")
    public String addBook(Model model){

            model.addAttribute("book", new Book());
    
            return "addbook";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {

        bookRepository.save(book);

        return "redirect:booklist";
    }
}
