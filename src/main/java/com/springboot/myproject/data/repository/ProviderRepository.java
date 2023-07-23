package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
