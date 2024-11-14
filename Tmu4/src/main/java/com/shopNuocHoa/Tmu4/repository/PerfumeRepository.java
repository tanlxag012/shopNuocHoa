package com.shopNuocHoa.Tmu4.repository;

import com.shopNuocHoa.Tmu4.models.perfume.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume,Long> {
    List<Perfume> findByNameContaining(String keyword);
    @Query("SELECT p FROM Perfume p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Perfume> searchProductsIgnoreCase(String keyword);
}
