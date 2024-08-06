package com.leonardo.awesomepizza.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ordine_pizza")
public class OrdinePizza {

	@EmbeddedId
	private OrdinePizzaId id;

	@ManyToOne
	@MapsId("pizzaId")
	@JoinColumn(name = "pizza_id")
	private Pizza pizza;

	@ManyToOne
	@MapsId("ordineId")
	@JoinColumn(name = "ordine_id")
	private Ordine ordine;

	private Integer quantita;

}
