package com.leonardo.awesomepizza.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.entity.OrdinePizzaId;

public interface OrdinePizzaRepository extends JpaRepository<OrdinePizza, OrdinePizzaId> {
	
	Optional<Set<OrdinePizza>> findByOrdineId(Long ordineId);
	
}
