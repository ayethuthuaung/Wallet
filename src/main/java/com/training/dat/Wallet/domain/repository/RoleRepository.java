package com.training.dat.Wallet.domain.repository;

import com.training.dat.Wallet.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
