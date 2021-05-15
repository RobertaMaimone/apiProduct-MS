package RobertaMaimone.com.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.BadRequestException;

public class ProductAlreadyRegisteredException extends BadRequestException {

    public ProductAlreadyRegisteredException(String nameProduct) {
        super(String.format("Produto com o nome %s já cadastrado no sistema.", nameProduct));
    }
}
