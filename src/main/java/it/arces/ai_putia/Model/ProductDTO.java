package it.arces.ai_putia.Model;

import lombok.Data;

@Data
public class ProductDTO {
   private Long id;
   private String pname;
   private String description;
   private Double price;
   private Long categoryId;
   private Boolean deleted;
}