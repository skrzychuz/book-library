package book.collections.homework.repository;

import book.collections.homework.model.response.model.AuthorRating;
import book.collections.homework.model.response.model.Book;

import java.util.List;

public interface BookLibraryRepo {

  Book getBookByIsbn(String isbn);

  List<Book> getBookListByCategory(String category);

  List<AuthorRating> getAuthorListOrderOfRating();

}
