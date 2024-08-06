package com.leonardo.awesomepizza.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.awesomepizza.model.dto.PizzaDto;
import com.leonardo.awesomepizza.service.PizzaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/awesome-pizza/pizze")
@RequiredArgsConstructor
public class PizzaController {
	
	private final PizzaService service;
	
	@GetMapping("/menu")
	public ResponseEntity<List<PizzaDto>> getMenuPizze(){
		return ResponseEntity.ok(service.getPizze());
	}
	
}
