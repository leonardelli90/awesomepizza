package com.leonardo.awesomepizza.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.model.dto.OrdinePizzaDto;
import com.leonardo.awesomepizza.model.dto.PizzaDto;
import com.leonardo.awesomepizza.repository.OrdinePizzaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdinePizzaServiceImpl implements OrdinePizzaService {

	private final OrdinePizzaRepository repository;
	private final PizzaService pizzaService;
	
	@Override
	public Set<OrdinePizzaDto> getListaPizze(Long ordineId) {

		Optional<Set<OrdinePizza>> ordiniPizze = repository.findByOrdineId(ordineId);
		
		if (ordiniPizze.isPresent()) {
			Set<OrdinePizzaDto> ordini = new HashSet<>();
			for (OrdinePizza op : ordiniPizze.get()) {
				PizzaDto pizzaDto = pizzaService.getPizzaById(op.getPizza().getId());
				OrdinePizzaDto opDto = convertEntityToDto(op);
				opDto.setPizza(pizzaDto);
				ordini.add(opDto);
			}
			return ordini;
		}
		
		return Collections.emptySet();
	}
	
	/*
	@Override
	@Transactional
	public void addOrdiniPizze(Set<OrdinePizza> ordiniPizze) {
		
		for (OrdinePizza ordinePizza : ordiniPizze) {
			repository.save(ordinePizza);
		}
		
	}
	*/
	
	@Override
	@Transactional
	public OrdinePizza addOrdinePizza(OrdinePizza ordinePizza) {
		return repository.save(ordinePizza);
	}
	
	private OrdinePizzaDto convertEntityToDto(OrdinePizza ordinePizza) {
		return OrdinePizzaDto.builder()
				.ordineId(ordinePizza.getId().getOrdineId())
				.pizzaId(ordinePizza.getId().getPizzaId())
				.quantita(ordinePizza.getQuantita())
				.build();
	}

}
