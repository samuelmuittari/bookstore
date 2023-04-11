package sm.bookstore.domain;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByid(String bookId);
    List<Book> findByTitle(String title);
    Book findById(Book book);
	void deleteById(Book book);

}
