package model;

import com.google.gson.Gson;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.bridge.builtin.EnumBridge;
import org.hibernate.search.bridge.builtin.IntegerBridge;

import javax.persistence.*;
import java.io.Serializable;

@Indexed
@Entity
@Table(name = "books", schema = "public")
public class Book implements Serializable {

    @Field(name = "bookIsbn")
    @Id
    @Column(name = "isbn")
    private String isbn;

    @Field
    @Column(name = "title")
    private String title;

    @Field
    @Column(name = "author")
    private String author;

    @Field
    @FieldBridge(impl = IntegerBridge.class)
    @Column(name = "year")
    private int year;

    @Field(bridge=@FieldBridge(impl=EnumBridge.class))
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    public enum Category {Fantasy, SciFi, Criminal, Science, Drama, Children, Horror, Poetry, Empty}


    public Book() {
    }

    public Book(String isbn, String title, String author, int year, Category category) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return category == book.category;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
