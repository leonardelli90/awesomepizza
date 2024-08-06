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
import com.leonardo.awesomepizza.enums.StatoEnum;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.controllastatoordine.ControllaStatoOrdineResponse;
import com.leonardo.awesomepizza.service.OrdineUtenteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdineUtenteServiceImplTest {
	
	@Autowired
	private OrdineUtenteService ordineUtenteService;

    @Test
    @Order(1)
    void testGetStatoOrdine() {
    	
    	ControllaStatoOrdineResponse statoOrdine = ordineUtenteService.getStatoOrdine(1L, 1L);
    	
        assertEquals(StatoEnum.IN_CODA.name(), statoOrdine.getStatoOrdine());

    }
    
    @Test
    @Order(2)
    void exceptionThrown_testGetStatoOrdine() {
    	
    	DatoNotFoundException datoNotFoundException = null;
    	
    	datoNotFoundException = assertThrows(DatoNotFoundException.class, () -> {
    		ordineUtenteService.getStatoOrdine(99L, 1L);
    	});
    	
    	assertEquals(MessaggioErroreEnum.UTENTE_NON_TROVATO.getMessage(99), datoNotFoundException.getMessage());
    	
    	datoNotFoundException = assertThrows(DatoNotFoundException.class, () -> {
    		ordineUtenteService.getStatoOrdine(1L, 100L);
    	});
    	
    	assertEquals(MessaggioErroreEnum.NO_ORDINE_PER_UTENTE.getMessage(1, 100), datoNotFoundException.getMessage());	

    }
    
}
