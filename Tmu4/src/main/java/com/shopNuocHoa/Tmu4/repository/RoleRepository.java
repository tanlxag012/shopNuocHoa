package com.shopNuocHoa.Tmu4.repository;

import com.shopNuocHoa.Tmu4.models.user.ERole;
import com.shopNuocHoa.Tmu4.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
