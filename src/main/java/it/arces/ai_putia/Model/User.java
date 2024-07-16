package it.arces.ai_putia.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName",
            unique = true,
            nullable = false)
    private String userName;

    @Column(name= "password",
            nullable = false)
    private String password;

    @Column(name = "email",
            unique = true,
            nullable = false)
    private String email;

    @Column(name = "firstname",
            nullable = false)
    private String firstName;

    @Column(name = "lastname",
            nullable = false)
    private String lastName;

    @Column(name = "phonenumber",
            unique = true)
    private String phonenumber;

    @Column(name = "isEnabled")
    private boolean isEnabled = false;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
