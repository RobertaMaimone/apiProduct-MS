package RobertaMaimone.com.github.controller;

import RobertaMaimone.com.github.dto.ProductDTO;
import RobertaMaimone.com.github.model.ProductEntity;
import RobertaMaimone.com.github.service.ProductService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
@Api( value = "API rest realiza crud de produtos")
public class ProductController {

    private ProductService productService;

    @Autowired
    ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo produto. ")
    public ResponseEntity<ProductEntity> newProduct(@RequestBody ProductEntity productEntity){
           ResponseEntity<ProductEntity> responseProductEntity =
                    this.productService.registersNewProduct(productEntity);
            return new ResponseEntity(responseProductEntity, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Pesquisa produto por id. ")
    public ResponseEntity<ProductDTO> searchByProduct(@PathVariable String id){
        ResponseEntity<ProductDTO> responseProductDTO = this.productService.searchByProductId(id);
        return new ResponseEntity(responseProductDTO, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Recupera lista de todos produtos. ")
    public ResponseEntity<List<ProductEntity>> searchAllProduct(){
          return this.productService.searchAllRegisteredProducts();

    }

    @GetMapping("/search")
    @ApiOperation(value = "Filtro de lista de produtos. ")
    public ResponseEntity<List<ProductEntity>> listOfFilteredProducts(
            @RequestParam(value = "q", required = false) String nameProduct,
            @RequestParam(value = "min_price", required = false) BigDecimal minPriceProduct,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPriceProduct){

        ResponseEntity<List<ProductEntity>>  responseEntity = this.productService.filteredProducts(nameProduct,minPriceProduct, maxPriceProduct);
         return new ResponseEntity(responseEntity, HttpStatus.OK);

    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualiza o produto. ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProductEntity> productUpdate( @PathVariable String id,
                                                        @RequestBody ProductDTO productDTO ) {
       ResponseEntity productEntity = this.productService.productByUpedate(id, productDTO);
       return new ResponseEntity(productEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Exclui produto. ")
    public ResponseEntity<Void> deleteProduct( @PathVariable String id ) {
         this.productService.productDelete(id);
         return ResponseEntity.ok().build();
    }
}