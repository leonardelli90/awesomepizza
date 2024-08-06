package com.leonardo.awesomepizza.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.awesomepizza.model.controllastatoordine.ControllaStatoOrdineResponse;
import com.leonardo.awesomepizza.service.OrdineUtenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/awesome-pizza/utenti")
@RequiredArgsConstructor
public class UtenteController {
	
	private final OrdineUtenteService oridneUtenteService;
	
	@GetMapping("/utente/{idUtente}/ordine/{idOrdine}")
	public ResponseEntity<ControllaStatoOrdineResponse> controllaStatoOrdine(@PathVariable Long idUtente, @PathVariable Long idOrdine){
		return ResponseEntity.ok(oridneUtenteService.getStatoOrdine(idUtente, idOrdine));
	}
	
}
