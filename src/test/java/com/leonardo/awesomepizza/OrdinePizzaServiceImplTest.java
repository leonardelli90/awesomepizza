package com.leonardo.awesomepizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leonardo.awesomepizza.entity.Ordine;
import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.entity.OrdinePizzaId;
import com.leonardo.awesomepizza.entity.Pizza;
import com.leonardo.awesomepizza.enums.StatoEnum;
import com.leonardo.awesomepizza.model.dto.OrdinePizzaDto;
import com.leonardo.awesomepizza.service.OrdinePizzaService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdinePizzaServiceImplTest {

	@Autowired
	private OrdinePizzaService ordinePizzaService;
	
	@Test
	@Order(1)
    void testGetListaPizze() {
		
		Set<OrdinePizzaDto> listaPizzeEmpty = ordinePizzaService.getListaPizze(99L);

		assertTrue(listaPizzeEmpty.isEmpty());
		
		Set<OrdinePizzaDto> listaPizze = ordinePizzaService.getListaPizze(1L);
		
		assertEquals(2, listaPizze.stream().count());
    }
	
	/*
	@Test
	@Order(2)
	void testAddOrdiniPizze() {
		
		Set<OrdinePizza> ordiniPizze = creaOrdinePizza1();
		
		ordinePizzaService.addOrdiniPizze(ordiniPizze);
		
		Set<OrdinePizzaDto> listaPizze = ordinePizzaService.getListaPizze(1L);
		assertEquals(3, listaPizze.stream().count());
		
	}
	*/
    
	@Test
	@Order(3)
	void testAddOrdinePizza() {
		
		OrdinePizza ordiniPizze = creaOrdinePizza2();
		
		OrdinePizza ordinePizza = ordinePizzaService.addOrdinePizza(ordiniPizze);
		
		assertEquals(StatoEnum.PRONTO.name(), ordinePizza.getOrdine().getStatoOrdine());
		assertEquals("Special", ordinePizza.getPizza().getNome());
	}
	
    /*
    private Set<OrdinePizza> creaOrdinePizza1() {
        Set<OrdinePizza> oridnePizze = new HashSet<>();
        oridnePizze.add(OrdinePizza.builder()
        		.id(OrdinePizzaId.builder()
        				.ordineId(1L)
        				.pizzaId(4L)
        				.build())
        		.pizza(Pizza.builder().id(4L).build())
        		.ordine(Ordine.builder().id(1L).build())
        		.quantita(2).build());
        return oridnePizze;
    }
    */
    
    private OrdinePizza creaOrdinePizza2() {
        return OrdinePizza.builder()
        		.id(OrdinePizzaId.builder()
        				.ordineId(1L)
        				.pizzaId(4L)
        				.build())
        		.pizza(Pizza.builder().id(4L).build())
        		.ordine(Ordine.builder().id(1L).build())
        		.quantita(2).build();
    }
	
}
