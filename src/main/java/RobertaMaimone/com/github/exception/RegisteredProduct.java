package RobertaMaimone.com.github.exception;

public class RegisteredProduct extends RuntimeException{

    public RegisteredProduct(String nameProduct){
        super("Produto já cadastrado" + nameProduct);
    }
}
