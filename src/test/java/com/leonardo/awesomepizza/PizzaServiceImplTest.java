package com.leonardo.awesomepizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.entity.OrdinePizzaId;
import com.leonardo.awesomepizza.enums.MessaggioErroreEnum;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.dto.PizzaDto;
import com.leonardo.awesomepizza.service.PizzaService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaServiceImplTest {

	@Autowired
	private PizzaService pizzaService;

	@Test
	@Order(1)
	void testGetPizzaById() {

		PizzaDto pizzaById = pizzaService.getPizzaById(1L);
		assertEquals("Margherita", pizzaById.getNome());

	}

	@Test
	@Order(2)
	void exceptionThrown_testGetPizzaById() {

		DatoNotFoundException exception = assertThrows(DatoNotFoundException.class, () -> {
			pizzaService.getPizzaById(99L);
		});

		assertEquals(MessaggioErroreEnum.PIZZA_NON_TROVATA.getMessage(99), exception.getMessage());

	}

	@Test
	@Order(3)
	void testGetPizze1() {

		List<PizzaDto> pizze = pizzaService.getPizze();
		assertEquals(4, pizze.stream().count());

	}

	@Test
	@Order(4)
	void testGetPizze2() {

		Set<OrdinePizza> listaPizze = creaSetPizze();
		List<PizzaDto> pizze = pizzaService.getPizze(listaPizze);

		assertEquals(2, pizze.stream().count());
		
	}
	
	@Test
	@Order(5)
	void exceptionThrown_testGetPizze2() {

		Set<OrdinePizza> listaPizze = creaSetPizze(99L);
		
		DatoNotFoundException exception = assertThrows(DatoNotFoundException.class, () -> {
			pizzaService.getPizze(listaPizze);
		});

		assertEquals(MessaggioErroreEnum.PIZZA_NON_TROVATA.getMessage(99), exception.getMessage());

	}

	private Set<OrdinePizza> creaSetPizze() {
		Set<OrdinePizza> setPizze = new HashSet<>();
		setPizze.add(OrdinePizza.builder().id(OrdinePizzaId.builder().pizzaId(1L).build()).build());
		setPizze.add(OrdinePizza.builder().id(OrdinePizzaId.builder().pizzaId(2L).build()).build());
		return setPizze;
	}
	
	private Set<OrdinePizza> creaSetPizze(Long id) {
		Set<OrdinePizza> setPizze = new HashSet<>();
		setPizze.add(OrdinePizza.builder().id(OrdinePizzaId.builder().pizzaId(id).build()).build());
		return setPizze;
	}
}
