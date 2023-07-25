package com.springboot.myproject.data.repository;

import com.springboot.myproject.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
