package com.leonardo.awesomepizza.service;

import java.util.Set;

import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.model.dto.OrdinePizzaDto;

public interface OrdinePizzaService {

	OrdinePizza addOrdinePizza(OrdinePizza ordinePizza);
	
	Set<OrdinePizzaDto> getListaPizze(Long ordineId);
}
