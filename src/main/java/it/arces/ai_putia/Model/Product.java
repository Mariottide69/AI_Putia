package it.arces.ai_putia.Model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pname",
            unique = true,
            nullable = false)
    private String pname;

    @Column(name= "description",
            nullable = false)
    private String description;

    @Column(name = "price",
            nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_category", 
                nullable = false)
    private Category category;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails;
}
