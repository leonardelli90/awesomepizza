package com.leonardo.awesomepizza.service;

import com.leonardo.awesomepizza.model.controllastatoordine.ControllaStatoOrdineResponse;

public interface OrdineUtenteService {
	
	ControllaStatoOrdineResponse getStatoOrdine(Long idUtente, Long idOrdine);
	
}
