package com.leonardo.awesomepizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leonardo.awesomepizza.enums.MessaggioErroreEnum;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.dto.UtenteDto;
import com.leonardo.awesomepizza.service.UtenteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UtenteServiceImplTest {
	
	@Autowired
	private UtenteService utenteService;
	
	@Test
	@Order(1)
	void testGetUtenteById() {
		
		UtenteDto utente = utenteService.getUtenteById(1L);
		assertEquals("leonardo.nardelli@email.it", utente.getEmail());
		
	}
	
	@Test
	@Order(2)
	void exceptionThrown_testGetUtenteById() {
		
		DatoNotFoundException datoNotFoundException = assertThrows(DatoNotFoundException.class, () -> {
			utenteService.getUtenteById(99L);
    	});
    	
    	assertEquals(MessaggioErroreEnum.UTENTE_NON_TROVATO.getMessage(99), datoNotFoundException.getMessage());
		
	}
	
}
