package com.training.dat.Wallet.domain.repository;

import com.training.dat.Wallet.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndPassword(String userId, String password);
	boolean existsByUserId(String string);

}
