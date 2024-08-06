package com.leonardo.awesomepizza.service;

import java.util.List;
import java.util.Set;

import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.entity.Pizza;
import com.leonardo.awesomepizza.model.dto.PizzaDto;

public interface PizzaService {

	PizzaDto getPizzaById(Long id);
	
	List<PizzaDto> getPizze();
	
	List<PizzaDto> getPizze(List<Pizza> listaPizze);
	
	List<PizzaDto> getPizze(Set<OrdinePizza> listaPizze);
}
