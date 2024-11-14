package com.shopNuocHoa.Tmu4.models.checkout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopNuocHoa.Tmu4.models.perfume.Perfume;
import com.shopNuocHoa.Tmu4.models.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "check_out")
public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phoneNumber;
    private String perfume;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "", referencedColumnName = "id")
    private Perfume perfumeId;
    private String quantity;
    private String total;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
