package com.leonardo.awesomepizza.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.awesomepizza.enums.StatoEnum;
import com.leonardo.awesomepizza.model.creaordine.CreaOrdineRequest;
import com.leonardo.awesomepizza.model.dto.OrdineDto;
import com.leonardo.awesomepizza.service.OrdineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/awesome-pizza/ordini")
@RequiredArgsConstructor
public class OrdineController {
	
	private final OrdineService service;
	
	@PostMapping()
	public ResponseEntity<OrdineDto> creaOrdine(@Valid @RequestBody CreaOrdineRequest ordine) {
		return ResponseEntity.ok(service.creaOrdine(ordine));
	}
	
	@PutMapping("/prepara-ordine")
	public ResponseEntity<OrdineDto> preparaOrdine() {
		return ResponseEntity.ok(service.preparaOrdine());
	}
	
	@PutMapping("/prepara-ordine/{id}")
	public ResponseEntity<OrdineDto> preparaOrdine(@PathVariable Long id) {
		return ResponseEntity.ok(service.preparaOrdineById(id));
	}

	@GetMapping("/{status}")
	public ResponseEntity<List<OrdineDto>> ordiniPerStato(@PathVariable StatoEnum status){
		return ResponseEntity.ok(service.getOrdiniPerStato(status));
	}
	

	@PutMapping("/completa-ordine/{id}")
	public ResponseEntity<OrdineDto> completaOrdine(@PathVariable Long id) {
		return ResponseEntity.ok(service.completaOrdine(id));
	}
	
}
