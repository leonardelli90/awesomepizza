package com.leonardo.awesomepizza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrdinePizzaDto {
	
	private Long ordineId;
	private Long pizzaId;
	private PizzaDto pizza;
	private Integer quantita;

}
