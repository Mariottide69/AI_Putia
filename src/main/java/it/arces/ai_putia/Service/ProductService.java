package it.arces.ai_putia.Service;

import it.arces.ai_putia.Model.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
   List<ProductDTO> getAllProducts();

   Optional<ProductDTO> getProductById(Long id);

   ProductDTO saveProduct(ProductDTO productDTO);

   void deleteProduct(Long id);
}