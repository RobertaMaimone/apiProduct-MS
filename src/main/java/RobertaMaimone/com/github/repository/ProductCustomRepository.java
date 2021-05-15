package RobertaMaimone.com.github.repository;

import RobertaMaimone.com.github.model.ProductEntity;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductCustomRepository {

    private final EntityManager entityManager;
    public ProductCustomRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<ProductEntity> productFind(String nameProduct, BigDecimal minPriceProduct , BigDecimal maxPriceProduct){

        String query = "select P from ProductEntity as P ";
        String condicao = "where";

        if (nameProduct != null){
            query += condicao + " P.nameProduct = :nameProduct";
            condicao = " and ";
        }

        if (minPriceProduct != null){
            query += condicao + " P.priceProduct <= :priceProduct";
            condicao = " and ";
        }

        if (maxPriceProduct != null){
            query += condicao + " P.priceProduct >= :priceProduct";

        }

        TypedQuery<ProductEntity> q = entityManager.createQuery(query, ProductEntity.class);

        if (nameProduct != null){
            q.setParameter("nameProduct", nameProduct);
        }

        if (minPriceProduct != null){
            q.setParameter("priceProduct", minPriceProduct);
        }

        if (maxPriceProduct != null){
            q.setParameter("priceProduct", maxPriceProduct);
        }

        return q.getResultList();
    }
}
