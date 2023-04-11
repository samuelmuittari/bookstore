package sm.bookstore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sm.bookstore.domain.Book;
import sm.bookstore.domain.BookRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    public void findByTitleShouldReturnTitle(){
        List<Book> books = repository.findByTitle("Allun elämä");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Allu");
    }

    @Test
    public void CreateNewBook(){
        Book book = new Book("Testi", "Testaaja", 2020, "123456", 5, null);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test // Testing bookrepository delete method
	public void DeleteBookTest() {
		Book book = new Book("Testi2", "Testaaja2", 2020, "99999", 9999, null);
		final Long id = testEntityManager.persistAndGetId(book, Long.class);
        repository.deleteById(id);
        testEntityManager.flush();
        Book after = testEntityManager.find(Book.class, id);
		repository.deleteById(book.getId());
		assertThat(after).isNull();
	}
}
