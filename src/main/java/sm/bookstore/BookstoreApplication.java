package sm.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sm.bookstore.domain.Book;
import sm.bookstore.domain.BookRepository;
import sm.bookstore.domain.Category;
import sm.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			categoryRepository.save(new Category("Horror"));
			categoryRepository.save(new Category("Drama"));
			categoryRepository.save(new Category("Action"));
			categoryRepository.save(new Category("Fantasy"));
			bookRepository.save(new Book("Allun elämä", "Allu", 2023, "123456", 5, categoryRepository.findByName("Horror").get(0)));
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
