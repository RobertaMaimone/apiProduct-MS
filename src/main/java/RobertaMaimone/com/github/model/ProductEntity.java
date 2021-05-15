package RobertaMaimone.com.github.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "Products")
public class ProductEntity {

    @Id
    private String idProduct;

    @NotEmpty(message = "{campo.nome.product.obrigatorio}")
    @Column(nullable = false, length = 150)
    private String nameProduct;

    @NotEmpty(message = "{campo.description.product.obrigatorio}")
    @Column(nullable = false, length = 100)
    private String descriptionProduct;

    @NotNull(message = "{campo.price.obrigatorio}")
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal priceProduct;

    public ProductEntity(){}

    public ProductEntity(String idProduct, String nameProduct, String descriptionProduct, BigDecimal priceProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }


    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }


    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct( String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }


    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }
}
