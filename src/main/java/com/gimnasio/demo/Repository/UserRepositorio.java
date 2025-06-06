package com.gimnasio.demo.Repository;

import com.gimnasio.demo.Model.Security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositorio extends JpaRepository<UserEntity, Integer> {
    boolean findByUsername(String username);
    UserEntity getByUsername(String username);
    Optional<UserEntity> findUserEntitiesByUsername(String username);
}
