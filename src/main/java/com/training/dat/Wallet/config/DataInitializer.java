package com.training.dat.Wallet.config;

import com.training.dat.Wallet.domain.model.Role;
import com.training.dat.Wallet.domain.model.User;
import com.training.dat.Wallet.domain.repository.RoleRepository;
import com.training.dat.Wallet.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Initialize roles
        Role adminRole = roleRepository.findById(1L).orElseGet(() -> roleRepository.save(new Role(1L, "Administrator")));
        Role userRole = roleRepository.findById(2L).orElseGet(() -> roleRepository.save(new Role(2L, "User")));

        // Initialize default users
        if (!userRepository.existsByUserId("admin")) {
            userRepository.save(new User("admin", "Administrator", "adminPass123", adminRole));
        }
        if (!userRepository.existsByUserId("user1")) {
            userRepository.save(new User("user1", "Standard User", "userPass123", userRole));
        }
    }
}
