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

import com.leonardo.awesomepizza.enums.MessaggioErroreEnum;
import com.leonardo.awesomepizza.enums.StatoEnum;
import com.leonardo.awesomepizza.exception.CheckException;
import com.leonardo.awesomepizza.exception.DatoNotFoundException;
import com.leonardo.awesomepizza.model.creaordine.CreaOrdineRequest;
import com.leonardo.awesomepizza.model.creaordine.OrdinePizzaModel;
import com.leonardo.awesomepizza.model.dto.OrdineDto;
import com.leonardo.awesomepizza.service.OrdineService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdineServiceImplTest {

	@Autowired
	private OrdineService ordineService;

    @Test
    @Order(1)
    void testPreparaOrdine() {

        OrdineDto result = ordineService.preparaOrdine();
        assertEquals(StatoEnum.IN_PREPARAZIONE.name(), result.getStatoOrdine());

    }
    
    @Test
    @Order(2)
    void exceptionThrown_testPreparaOrdine() {

    	DatoNotFoundException exception = assertThrows(DatoNotFoundException.class, () -> {
    		ordineService.preparaOrdine();
        });
    	
        assertEquals(MessaggioErroreEnum.NO_ORDINI_IN_CODA.getMessage(), exception.getMessage());

    }

    @Test
    @Order(3)
    void testCompletaOrdine() {
    	OrdineDto result = ordineService.completaOrdine(1L);
    	assertEquals(StatoEnum.PRONTO.name(), result.getStatoOrdine());
    }
    
    @Test
    @Order(4)
    void exceptionThrown_testCompletaOrdine() {
    	
    	DatoNotFoundException datoNotFoundException = assertThrows(DatoNotFoundException.class, () -> {
    		ordineService.completaOrdine(99L);
    	});
    	
    	assertEquals(MessaggioErroreEnum.ORDINE_NON_TROVATO.getMessage(99), datoNotFoundException.getMessage());
    	
    	CheckException checkException = assertThrows(CheckException.class, () -> {
    		ordineService.completaOrdine(1L);
    	});
    	
    	assertEquals(MessaggioErroreEnum.ORDINE_NON_COMPLETABILE.getMessage(1), checkException.getMessage());

    }
    
    @Test
    @Order(5)
    void testCreaOrdine() {

    	CreaOrdineRequest ordineRequest = creaOrdineDto();
        OrdineDto result = ordineService.creaOrdine(ordineRequest);

        assertEquals(ordineRequest.getIdUtente(), result.getUtente().getId());
        assertEquals(StatoEnum.IN_CODA.name(), result.getStatoOrdine());
        assertEquals(2L, result.getId());
    }
    
    @Test
    @Order(6)
    void exceptionThrown_testCreaOrdine() {
    	
    	CreaOrdineRequest ordineRequest = creaOrdineDto(99L);
    	
    	DatoNotFoundException datoNotFoundException = assertThrows(DatoNotFoundException.class, () -> {
    		ordineService.creaOrdine(ordineRequest);
    	});
    	
    	assertEquals(MessaggioErroreEnum.UTENTE_NON_TROVATO.getMessage(99), datoNotFoundException.getMessage());
    }

    @Test
    @Order(7)
    void testGetOrdiniPerStato() {
    	List<OrdineDto> ordiniPerStatoTutti = ordineService.getOrdiniPerStato(StatoEnum.TUTTI);
    	assertEquals(2, ordiniPerStatoTutti.stream().count());
    	
    	List<OrdineDto> ordiniPerStatoInCoda = ordineService.getOrdiniPerStato(StatoEnum.IN_CODA);
    	assertEquals(1, ordiniPerStatoInCoda.stream().count());
    }
    
    private CreaOrdineRequest creaOrdineDto() {
    	return creaOrdineDto(1L);
    }
    
    private CreaOrdineRequest creaOrdineDto(Long idUtente) {
        Set<OrdinePizzaModel> listaPizze = new HashSet<>();
        listaPizze.add(OrdinePizzaModel.builder().pizzaId(1L).quantita(2).build());
        listaPizze.add(OrdinePizzaModel.builder().pizzaId(2L).quantita(1).build());
        return CreaOrdineRequest.builder().idUtente(idUtente).ordinePizze(listaPizze).build();
    }
}
