package com.leonardo.awesomepizza.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.awesomepizza.entity.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
	
	Optional<Ordine> findTopByStatoOrdineOrderByDataCreazioneAsc(String statoOrdine);
	
	List<Ordine> findByStatoOrdine(String statoOrdine);
	
	Optional<Ordine> findByIdAndUtenteId(Long id, Long utenteId);
	
}
