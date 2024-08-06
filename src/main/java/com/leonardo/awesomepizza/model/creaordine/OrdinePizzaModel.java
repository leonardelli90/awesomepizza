package com.leonardo.awesomepizza.model.creaordine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrdinePizzaModel {
	
	private Long pizzaId;
	private Integer quantita;
	
}
