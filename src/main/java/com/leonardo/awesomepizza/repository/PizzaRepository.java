package com.leonardo.awesomepizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.awesomepizza.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{

}
