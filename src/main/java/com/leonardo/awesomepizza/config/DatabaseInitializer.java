package com.leonardo.awesomepizza.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leonardo.awesomepizza.entity.Ordine;
import com.leonardo.awesomepizza.entity.OrdinePizza;
import com.leonardo.awesomepizza.entity.OrdinePizzaId;
import com.leonardo.awesomepizza.entity.Pizza;
import com.leonardo.awesomepizza.entity.Utente;
import com.leonardo.awesomepizza.enums.StatoEnum;
import com.leonardo.awesomepizza.repository.OrdinePizzaRepository;
import com.leonardo.awesomepizza.repository.OrdineRepository;
import com.leonardo.awesomepizza.repository.PizzaRepository;
import com.leonardo.awesomepizza.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final UtenteRepository utenteRepository;
    private final PizzaRepository pizzaRepository;
    private final OrdineRepository ordineRepository;
    private final OrdinePizzaRepository ordinePizzaRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            utenteRepository.save(Utente.builder()
            		.username("leonardelli")
            		.password("leonardo")
            		.email("leonardo.nardelli@email.it")
            		.build());
            pizzaRepository.save(Pizza.builder()
            		.nome("Margherita")
            		.descrizione("Pizza con pomodoro e mozzarella")
            		.prezzo(new BigDecimal("5.00"))
            		.build());
            pizzaRepository.save(Pizza.builder()
            		.nome("Diavola")
            		.descrizione("Pizza con pomodoro, mozzarella e salsiccia piccante")
            		.prezzo(new BigDecimal("8.00"))
            		.build());
            pizzaRepository.save(Pizza.builder()
            		.nome("Quattro Stagioni")
            		.descrizione("Pizza con quattro stagioni")
            		.prezzo(new BigDecimal("10.00"))
            		.build());
            pizzaRepository.save(Pizza.builder()
            		.nome("Special")
            		.descrizione("")
            		.prezzo(new BigDecimal("15.00"))
            		.build());
            ordineRepository.save(Ordine.builder()
            		.statoOrdine(StatoEnum.IN_CODA.name())
            		.utente(Utente.builder().id(1L).build())
            		.build());
            ordinePizzaRepository.save(OrdinePizza.builder()
            		.id(OrdinePizzaId.builder().pizzaId(1L).ordineId(1L).build())
            		.pizza(Pizza.builder().id(1L).build())
            		.ordine(Ordine.builder().id(1L).build())
            		.quantita(2)
            		.build());
            ordinePizzaRepository.save(OrdinePizza.builder()
            		.id(OrdinePizzaId.builder().pizzaId(2L).ordineId(1L).build())
            		.pizza(Pizza.builder().id(2L).build())
            		.ordine(Ordine.builder().id(1L).build())
            		.quantita(1)
            		.build());
        };
    }
}
