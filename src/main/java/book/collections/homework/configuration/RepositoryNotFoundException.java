package book.collections.homework.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No results found")
public class RepositoryNotFoundException extends RuntimeException {

}
