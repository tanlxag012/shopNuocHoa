package com.shopNuocHoa.Tmu4.models.user;

import com.shopNuocHoa.Tmu4.models.checkout.CheckOut;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<CheckOut> checkOuts;
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User() {
    }
}
