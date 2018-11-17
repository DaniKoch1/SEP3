package controller;

import com.google.gson.Gson;
import controller.connection.DatabaseConnection;
import controller.connection.DatabaseProxy;
import controller.connection.MockDatabase;
import model.Book;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Controller {

    private DatabaseProxy db;
    private Gson gson = new Gson();

    @Autowired
    Controller(DatabaseProxy db) {
        this.db = db;
    }

    public List<Book> search(String searchTerm) throws DatabaseConnection.ServerOfflineException, DatabaseConnection.SearchException {
        return db.search(searchTerm);
    }

    public List<Book> advancedSearch(String title, String author, int year, String isbn, Book.Category category) throws DatabaseConnection.ServerOfflineException, DatabaseConnection.SearchException {
        return db.advancedSearch(title, author, year, isbn, category);
    }

    public String getBookDetails(String isbn) {
        return db.getBookDetails(isbn);
    }

    public String addCustomer(Customer customer){
        return db.addCustomer(customer);
    }
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        Controller controller = new Controller(db);
    }
}
