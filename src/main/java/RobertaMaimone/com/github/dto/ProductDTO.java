package RobertaMaimone.com.github.dto;

import RobertaMaimone.com.github.model.ProductEntity;

import java.math.BigDecimal;

public class ProductDTO {

    private String idProduct;
    private String nameProduct;
    private String descriptionProduct;
    private BigDecimal priceProduct;

    public ProductDTO(){};

    public ProductDTO(String idProduct, String nameProduct, String descriptionProduct, BigDecimal priceProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
    }

    public static ProductDTO converter(ProductEntity productEntity) {
        ProductDTO product = new ProductDTO();
        product.setIdProduct(productEntity.getIdProduct());
        product.setNameProduct(productEntity.getNameProduct());
        product.setDescriptionProduct(productEntity.getDescriptionProduct());
        product.setPriceProduct(productEntity.getPriceProduct());
        return product;
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

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }
}
