package com.shopNuocHoa.Tmu4.models.perfume;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopNuocHoa.Tmu4.models.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "perfume")
@Data
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "perfume_type_id") // Đây là cột khóa ngoại trong bảng PERFUME
    @JsonIgnore
    private PerfumeType perfumeType;
}

