package com.leonardo.awesomepizza.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.leonardo.awesomepizza.entity.Utente;
import com.leonardo.awesomepizza.enums.MessaggioErroreEnum;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.dto.UtenteDto;
import com.leonardo.awesomepizza.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService {
	
	private final UtenteRepository repository;
	
	@Override
	public UtenteDto getUtenteById(Long id) {
		
		Optional<Utente> utenteOpt = repository.findById(id);
		
		if (utenteOpt.isPresent()) {
			return convertEntityToDto(utenteOpt.get());
		} else {
			throw new DatoNotFoundException(MessaggioErroreEnum.UTENTE_NON_TROVATO.getMessage(id));
		}
	}

	@Override
	public boolean utenteEsiste(Long id) {
		return repository.existsById(id);
	}
	
	private UtenteDto convertEntityToDto(Utente utente) {
		return UtenteDto.builder()
				.id(utente.getId())
				.username(utente.getUsername())
				.email(utente.getEmail())
				.build();
    }

	
}
