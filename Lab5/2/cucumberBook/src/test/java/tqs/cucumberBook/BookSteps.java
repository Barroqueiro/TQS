package tqs.cucumberBook;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
 
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class BookSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
	
	@ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime iso8601Date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }
	
	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author,  final LocalDateTime ldt ) {
		Book book = new Book(title, author, Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
		System.out.println(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
		library.addBook(book);
	}
 
	@When("the customer searches for books written by {string}")
	public void setSearchParameters(final String name) {
		result = library.findBooksByAuthor(name);
	}
	
	@When("the customer searches for books called {string}")
	public void setSearchParametersName(final String name) {
		result = library.findBooksByName(name);
	}
	
	@When("the customer searches for books published between {int} and {int}")
	public void setSearchParametersDate(final int year1, final int year2) {
		LocalDateTime l1=LocalDateTime.of(year1,1, 1,0, 0);
		Date d1 = (Date) Date.from(l1.atZone(ZoneId.systemDefault()).toInstant());
		LocalDateTime l2=LocalDateTime.of(year2,12, 31,0, 0);
		Date d2 = (Date) Date.from(l2.atZone(ZoneId.systemDefault()).toInstant());
		result = library.findBooks(d1, d2);
	}
	
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}
}
