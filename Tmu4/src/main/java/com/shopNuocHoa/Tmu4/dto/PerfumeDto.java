package com.shopNuocHoa.Tmu4.dto;

import lombok.Data;

@Data
public class PerfumeDto {
    private String id;
    private String name;
    private String description;
    private Long price;
    private Integer quantity;
    private String perfumeType;
}
