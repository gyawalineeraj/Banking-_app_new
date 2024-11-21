package com.ng.NgBank;

import com.ng.NgBank.controller.AuthController;
import com.ng.NgBank.entity.Role;
import com.ng.NgBank.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class NgBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgBankApplication.class, args);

	}

	private final RoleRepository roleRepository;

	@PostConstruct
	public void doSth() {
		Role role = Role.builder()
				.name("ADMIN")
				.build();
		Role role1 = Role.builder()
				.name("USER")
				.build();
		try {
			roleRepository.save(role);
			roleRepository.save(role1);
		} catch (Exception e) {

		}

	}
}

