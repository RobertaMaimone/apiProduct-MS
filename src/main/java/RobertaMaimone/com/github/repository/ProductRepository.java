package RobertaMaimone.com.github.repository;

import RobertaMaimone.com.github.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

  //  Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findByNameProduct(String nameProduct);
}
