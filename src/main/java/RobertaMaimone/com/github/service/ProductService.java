package RobertaMaimone.com.github.service;
import RobertaMaimone.com.github.dto.ProductDTO;
import RobertaMaimone.com.github.exception.ProductAlreadyRegisteredException;

import RobertaMaimone.com.github.exception.ProductNotFoundException;
import RobertaMaimone.com.github.model.ProductEntity;
import RobertaMaimone.com.github.repository.ProductCustomRepository;
import RobertaMaimone.com.github.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductCustomRepository productCustomRepository;

    @Autowired
    ProductService(ProductRepository productRepository,
                   ProductCustomRepository productCustomRepository){
        this.productRepository = productRepository;
        this.productCustomRepository = productCustomRepository;
    }

    private String productIdGenerator(){
        String uniqueKey = UUID.randomUUID().toString();
        return uniqueKey;
    }

    private BigDecimal checkIfNumberIsNegative(BigDecimal priceProduct){

        if (priceProduct.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor informado não é válido");{
        }
        return priceProduct;
        }
    private ProductEntity validationOfNotnullFields(String nameProduct, String descriptionProduct)throws IllegalArgumentException{

       if(nameProduct.trim().isEmpty() || nameProduct.isEmpty()) throw new IllegalArgumentException("O valor não deve ser nulo ou vazio");{
          if(descriptionProduct.trim().isEmpty() || descriptionProduct.isEmpty())  throw new IllegalArgumentException("O valor não deve ser nulo ou vazio");{
           }
       }
       ProductEntity validatedfield = new ProductEntity();
       validatedfield.setNameProduct(nameProduct.trim());
       validatedfield.setDescriptionProduct(descriptionProduct.trim());
       return validatedfield;
    }

   public ResponseEntity<ProductEntity> registersNewProduct(ProductEntity productEntity) throws ProductAlreadyRegisteredException {
        String idGenerated = this.productIdGenerator();

        ProductEntity validatedFields =
                this.validationOfNotnullFields(productEntity.getNameProduct(),
                        productEntity.getDescriptionProduct());

        BigDecimal validatedPrice = this.checkIfNumberIsNegative(productEntity.getPriceProduct());
        validatedFields.setIdProduct(idGenerated);
        validatedFields.setPriceProduct(validatedPrice);
        ProductEntity responseProductEntity = this.productRepository.save(validatedFields);

        return new ResponseEntity(responseProductEntity, HttpStatus.CREATED);
    }

    public ResponseEntity<ProductDTO> searchByProductId(String idProduct) throws ProductNotFoundException {
        ProductDTO productDTO = new ProductDTO();
        Optional<ProductEntity> productEntity = this.productRepository.findById(idProduct);
        productDTO.setIdProduct(productEntity.get().getIdProduct());
        productDTO.setNameProduct(productEntity.get().getNameProduct());
        productDTO.setDescriptionProduct(productEntity.get().getDescriptionProduct());
        productDTO.setPriceProduct(productEntity.get().getPriceProduct());
        return new ResponseEntity(productDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductEntity>> searchAllRegisteredProducts(){
        List<ProductEntity> productEntity = this.productRepository.findAll();
          return ResponseEntity.ok().body(productEntity);
    }

    public ResponseEntity<Void> productDelete(String id) throws ProductNotFoundException{
        ProductEntity productEntity = this.productRepository.findById(id).get();
         this.productRepository.delete(productEntity);
         return new ResponseEntity(HttpStatus.OK);

    }

    public ResponseEntity<ProductEntity> productByUpedate(String id, ProductDTO productDTO) throws ProductNotFoundException {

        ProductEntity productEntity = this.productRepository.findById(id).get();

        productEntity.setNameProduct(productDTO.getNameProduct());
        productEntity.setDescriptionProduct(productDTO.getDescriptionProduct());
        productEntity.setPriceProduct(productDTO.getPriceProduct());
        ProductEntity responseProductEntity = productRepository.save(productEntity);
        return new ResponseEntity(responseProductEntity, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductEntity>> filteredProducts(String nameProduct, BigDecimal minPriceProduct,
                                                                      BigDecimal maxPriceProduct) {
        List<ProductEntity> productEntityList = this.productCustomRepository.productFind(nameProduct,minPriceProduct,maxPriceProduct);
//                .stream()
//                .map(ProductDTO::converter)
//                .collect(Collectors.toList());
        return ResponseEntity.ok().body(productEntityList);

    }
}
