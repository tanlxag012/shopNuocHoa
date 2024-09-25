package com.shopNuocHoa.Tmu4.models.perfume;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "perfume_type")
@Data
public class PerfumeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "perfumeType")
    @JsonIgnore
    private Set<Perfume> perfumes;
}

