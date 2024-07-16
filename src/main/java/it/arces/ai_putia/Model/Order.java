package it.arces.ai_putia.Model;

import java.time.LocalDateTime;

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
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @Column(name = "payment_at")
    private LocalDateTime payment_at;

    @Column(name = "payment_code", unique = true)
    private String payment_code;

    @Column(name = "closed")
    private Boolean closed;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;
        
}
