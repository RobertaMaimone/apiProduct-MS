package RobertaMaimone.com.github.exception;

import javax.ws.rs.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException(String nameProduct) {
        super(String.format("Produto com o nome %s não encontrado no sistema.", nameProduct));
    }
}
