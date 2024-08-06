package com.leonardo.awesomepizza.service;

import org.springframework.stereotype.Service;

import com.leonardo.awesomepizza.enums.MessaggioErroreEnum;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.controllastatoordine.ControllaStatoOrdineResponse;
import com.leonardo.awesomepizza.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdineUtenteServiceImpl implements OrdineUtenteService {

	private final UtenteRepository utenteRepository;
	private final OrdineService ordineService;

	@Override
	public ControllaStatoOrdineResponse getStatoOrdine(Long idUtente, Long idOrdine) {
		
		if (utenteRepository.existsById(idUtente)) {

			String statoOrdine = ordineService.getStatoOrdine(idUtente, idOrdine);
			return ControllaStatoOrdineResponse.builder().statoOrdine(statoOrdine).build(); 
			
		}
		
		throw new DatoNotFoundException(MessaggioErroreEnum.UTENTE_NON_TROVATO.getMessage(idUtente));
	}

}
