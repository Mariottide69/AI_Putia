package it.arces.ai_putia.Repository;

// import it.arces.ai_putia.Service.ProductService;
import it.arces.ai_putia.Model.Product;
// import it.arces.ai_putia.Model.ProductDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   
}