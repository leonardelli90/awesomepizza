package com.leonardo.awesomepizza.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrdineDto {
	
	private Long id;
	
	private String statoOrdine;
	private UtenteDto utente;
	private Set<OrdinePizzaDto> listaPizze;
	
	private LocalDateTime dataCreazione;
	private LocalDateTime dataAggiornamento;
	
}	
