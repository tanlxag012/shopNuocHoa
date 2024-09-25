package com.shopNuocHoa.Tmu4.repository;

import com.shopNuocHoa.Tmu4.models.perfume.PerfumeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfumeTypeRepository extends JpaRepository<PerfumeType,Long> {
    PerfumeType findByName(String name);
}
