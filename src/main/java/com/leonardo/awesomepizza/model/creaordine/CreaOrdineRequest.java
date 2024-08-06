package com.leonardo.awesomepizza.model.creaordine;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreaOrdineRequest {
	
	@NotNull
	private Long idUtente;
	
	@NotNull
	private Set<OrdinePizzaModel> ordinePizze;
	
}
