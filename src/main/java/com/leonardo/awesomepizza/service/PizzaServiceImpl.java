package com.leonardo.awesomepizza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.entity.Pizza;
import com.leonardo.awesomepizza.enums.MessaggioErroreEnum;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.dto.PizzaDto;
import com.leonardo.awesomepizza.repository.PizzaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

	private final PizzaRepository repository;

	@Override
	public PizzaDto getPizzaById(Long id) {
		Optional<Pizza> pizza = repository.findById(id);

		if (pizza.isPresent()) {
			return convertEntityToDto(pizza.get());
		} else {
			throw new DatoNotFoundException(MessaggioErroreEnum.PIZZA_NON_TROVATA.getMessage(id));
		}
		
	}

	@Override
	public List<PizzaDto> getPizze() {
		List<Pizza> listaPizze = repository.findAll();
		return getListaPizzeDto(listaPizze);
	}

	@Override
	public List<PizzaDto> getPizze(List<Pizza> listaPizze) {
		return getListaPizzeDto(listaPizze);
	}

	@Override
	public List<PizzaDto> getPizze(Set<OrdinePizza> listaPizze) {
		return getListaPizzeDto(listaPizze);
	}

	private List<PizzaDto> getListaPizzeDto(List<Pizza> listaPizze) {
		List<PizzaDto> pizze = new ArrayList<>();
		for (Pizza pizza : listaPizze) {
			pizze.add(getPizzaById(pizza.getId()));
		}

		return pizze;
	}

	private List<PizzaDto> getListaPizzeDto(Set<OrdinePizza> listaPizze) {
		List<PizzaDto> pizze = new ArrayList<>();
		for (OrdinePizza ordinePizza : listaPizze) {
			pizze.add(getPizzaById(ordinePizza.getId().getPizzaId()));
		}

		return pizze;
	}

	private PizzaDto convertEntityToDto(Pizza pizza) {
		return PizzaDto.builder()
				.id(pizza.getId())
				.nome(pizza.getNome())
				.descrizione(pizza.getDescrizione())
				.prezzo(pizza.getPrezzo())
				.build();
	}

}
