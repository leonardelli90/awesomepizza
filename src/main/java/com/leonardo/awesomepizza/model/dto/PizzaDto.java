package com.leonardo.awesomepizza.model.	dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PizzaDto {
	
	private Long id;
	private String nome;
	private String descrizione;
	private BigDecimal prezzo;

}
