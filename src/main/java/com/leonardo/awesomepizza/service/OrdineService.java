package com.leonardo.awesomepizza.service;

import java.util.List;

import com.leonardo.awesomepizza.enums.StatoEnum;
import com.leonardo.awesomepizza.model.creaordine.CreaOrdineRequest;
import com.leonardo.awesomepizza.model.dto.OrdineDto;

public interface OrdineService {
	
	//OrdineDto creaOrdine(OrdineDto ordine);
	OrdineDto creaOrdine(CreaOrdineRequest ordine);
	
	OrdineDto preparaOrdine();
	
	OrdineDto preparaOrdineById(Long id);
	
	List<OrdineDto> getOrdiniPerStato(StatoEnum stato);
	
	OrdineDto completaOrdine(Long id);
	
	String getStatoOrdine(Long idUtente, Long idOrdine);
	
}
