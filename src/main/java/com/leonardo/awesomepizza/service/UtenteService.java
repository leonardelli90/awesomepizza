package com.leonardo.awesomepizza.service;

import com.leonardo.awesomepizza.model.dto.UtenteDto;

public interface UtenteService {
	
	boolean utenteEsiste(Long id);
	
	UtenteDto getUtenteById(Long id);
	
}
