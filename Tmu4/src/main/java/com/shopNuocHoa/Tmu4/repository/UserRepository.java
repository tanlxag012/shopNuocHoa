package com.shopNuocHoa.Tmu4.repository;

import com.shopNuocHoa.Tmu4.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
}
