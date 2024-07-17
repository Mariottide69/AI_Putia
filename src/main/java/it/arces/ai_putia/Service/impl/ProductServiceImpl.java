package it.arces.ai_putia.Service.impl;

import it.arces.ai_putia.Model.ProductDTO;
import it.arces.ai_putia.Model.Product;
import it.arces.ai_putia.Model.Category;
import it.arces.ai_putia.Repository.ProductRepository;
import it.arces.ai_putia.Repository.CategoryRepository;
import it.arces.ai_putia.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductRepository productRepository;

   @Autowired
   private CategoryRepository categoryRepository;

   @Override
   public List<ProductDTO> getAllProducts() {
      return productRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
   }

   @Override
   public Optional<ProductDTO> getProductById(Long id) {
      return productRepository.findById(id).map(this::convertToDTO);
   }

   @Override
   public ProductDTO saveProduct(ProductDTO productDTO) {
      Product product = convertToEntity(productDTO);
      product = productRepository.save(product);
      return convertToDTO(product);
   }

   @Override
   public void deleteProduct(Long id) {
      productRepository.deleteById(id);
   }

   private ProductDTO convertToDTO(Product product) {
      ProductDTO dto = new ProductDTO();
      dto.setId(product.getId());
      dto.setPname(product.getPname());
      dto.setDescription(product.getDescription());
      dto.setPrice(product.getPrice());
      dto.setCategoryId(product.getCategory().getId());
      dto.setDeleted(product.getDeleted());
      return dto;
   }

   private Product convertToEntity(ProductDTO dto) {
      Product product = new Product();
      product.setId(dto.getId());
      product.setPname(dto.getPname());
      product.setDescription(dto.getDescription());
      product.setPrice(dto.getPrice());
      product.setDeleted(dto.getDeleted());

      Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
      product.setCategory(category);

      return product;
   }
}