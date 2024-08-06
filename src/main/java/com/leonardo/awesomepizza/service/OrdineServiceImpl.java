package com.leonardo.awesomepizza.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.leonardo.awesomepizza.entity.Ordine;
import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.entity.OrdinePizzaId;
import com.leonardo.awesomepizza.entity.Pizza;
import com.leonardo.awesomepizza.entity.Utente;
import com.leonardo.awesomepizza.enums.MessaggioErroreEnum;
import com.leonardo.awesomepizza.enums.StatoEnum;
import com.leonardo.awesomepizza.exception.CheckException;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.creaordine.CreaOrdineRequest;
import com.leonardo.awesomepizza.model.creaordine.OrdinePizzaModel;
import com.leonardo.awesomepizza.model.dto.OrdineDto;
import com.leonardo.awesomepizza.model.dto.OrdinePizzaDto;
import com.leonardo.awesomepizza.model.dto.UtenteDto;
import com.leonardo.awesomepizza.repository.OrdineRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdineServiceImpl implements OrdineService {

	private final OrdineRepository ordineRepository;
	private final OrdinePizzaService ordinePizzaService;
	private final UtenteService utenteService;

	@Override
	@Transactional
	public OrdineDto creaOrdine(CreaOrdineRequest request) {

		if (utenteService.utenteEsiste(request.getIdUtente())) {
		
			Ordine ordineEntity = ordineRepository
					.save(Ordine.builder()
							.statoOrdine(StatoEnum.IN_CODA.name())
							.utente(Utente.builder()
									.id(request.getIdUtente())
									.build())
							.build());
			
			Set<OrdinePizza> listaPizzeTest = new HashSet<>();
			
			for (OrdinePizzaModel ordinePizza : request.getOrdinePizze()) {
				
				OrdinePizza op = OrdinePizza.builder()
					.id(OrdinePizzaId.builder().ordineId(ordineEntity.getId()).pizzaId(ordinePizza.getPizzaId()).build())
					.pizza(Pizza.builder().id(ordinePizza.getPizzaId()).build())
					.ordine(Ordine.builder().id(ordineEntity.getId()).build())
					.quantita(ordinePizza.getQuantita())
					.build();
				
				ordinePizzaService.addOrdinePizza(op);
				
				listaPizzeTest.add(op);
	
			}
			
			ordineEntity.setOrdinePizza(listaPizzeTest);
			
			return convertEntityToDto(ordineEntity);
		}
		
		throw new DatoNotFoundException(MessaggioErroreEnum.UTENTE_NON_TROVATO.getMessage(request.getIdUtente()));
	}

	@Override
	public OrdineDto preparaOrdine() {

		Optional<Ordine> ordineOpt = ordineRepository
				.findTopByStatoOrdineOrderByDataCreazioneAsc(StatoEnum.IN_CODA.name());
		
		if (ordineOpt.isPresent()) {
			return aggiornaStatoOrdine(ordineOpt.get(), StatoEnum.IN_PREPARAZIONE);
		}
		
		throw new DatoNotFoundException(MessaggioErroreEnum.NO_ORDINI_IN_CODA.getMessage());
	}

	@Override
	public OrdineDto preparaOrdineById(Long id) {

		Optional<Ordine> ordineOpt = ordineRepository.findById(id);
		if (ordineOpt.isPresent()) { 
			if (ordineOpt.get().getStatoOrdine().equals(StatoEnum.IN_CODA.name())) {
				return aggiornaStatoOrdine(ordineOpt.get(), StatoEnum.IN_PREPARAZIONE);
			}
			throw new CheckException(MessaggioErroreEnum.STATO_ORDINE_NON_MODIFICABILE.getMessage(id));
		}
		
		throw new DatoNotFoundException(MessaggioErroreEnum.ORDINE_NON_TROVATO.getMessage(id));
	}
	
	@Override
	public List<OrdineDto> getOrdiniPerStato(StatoEnum stato) {
		
		List<OrdineDto> listaOrdini = null;
		if (stato.equals(StatoEnum.TUTTI)) {
			listaOrdini = ordineRepository.findAll().stream().map(this::convertEntityToDto).toList();
		} else {
			listaOrdini = ordineRepository.findByStatoOrdine(stato.name()).stream().map(this::convertEntityToDto).toList();
		}
		
		return listaOrdini;
	}

	@Override
	public OrdineDto completaOrdine(Long id) {
		
		Optional<Ordine> ordineOpt = ordineRepository.findById(id);
		if (ordineOpt.isPresent()) {
			if (ordineOpt.get().getStatoOrdine().equals(StatoEnum.IN_PREPARAZIONE.name())) {
				return aggiornaStatoOrdine(ordineOpt.get(), StatoEnum.PRONTO);
			}
			throw new CheckException(MessaggioErroreEnum.ORDINE_NON_COMPLETABILE.getMessage(id));
		}

		throw new DatoNotFoundException(MessaggioErroreEnum.ORDINE_NON_TROVATO.getMessage(id));
		
	}
	
	@Override
	public String getStatoOrdine(Long idUtente, Long idOrdine) {
		
		Optional<Ordine> ordineOpt = ordineRepository.findByIdAndUtenteId(idOrdine, idUtente);
		if (ordineOpt.isPresent()) {
			return ordineOpt.get().getStatoOrdine();
		}
		
		throw new DatoNotFoundException(MessaggioErroreEnum.NO_ORDINE_PER_UTENTE.getMessage(idUtente, idOrdine));
		
	}

	private OrdineDto aggiornaStatoOrdine(Ordine ordine, StatoEnum stato) {
		ordine.setStatoOrdine(stato.name());
		ordineRepository.save(ordine);
		return convertEntityToDto(ordine);
	}

	private OrdineDto convertEntityToDto(Ordine ordine) {

		UtenteDto utente = utenteService.getUtenteById(ordine.getUtente().getId());
		Set<OrdinePizzaDto> pizze = ordinePizzaService.getListaPizze(ordine.getId());

		return OrdineDto.builder()
				.id(ordine.getId())
				.statoOrdine(ordine.getStatoOrdine())
				.utente(utente)
				.listaPizze(pizze)
				.dataCreazione(ordine.getDataCreazione())
				.dataAggiornamento(ordine.getDataAggiornamento())
				.build();
	}

}
