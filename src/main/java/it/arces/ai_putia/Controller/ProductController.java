package it.arces.ai_putia.Controller;

import it.arces.ai_putia.Model.ProductDTO;
import it.arces.ai_putia.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

   @Autowired
   private ProductService productService;

   @GetMapping
   public ResponseEntity<List<ProductDTO>> getAllProducts() {
      return ResponseEntity.ok(productService.getAllProducts());
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
      return productService.getProductById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping("/add")
   public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
      try {
         ProductDTO createdProduct = productService.saveProduct(productDTO);
         return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
      } catch (RuntimeException e) {
         return ResponseEntity.badRequest().body(null);
      }
   }

   @PutMapping("/{id}")
   public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
      productDTO.setId(id);
      return ResponseEntity.ok(productService.saveProduct(productDTO));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
      productService.deleteProduct(id);
      return ResponseEntity.noContent().build();
   }
}