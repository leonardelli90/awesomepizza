package com.leonardo.awesomepizza.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.leonardo.awesomepizza.entity.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pizze")
public class Pizza extends BaseEntity {

	@Column(nullable = false)
	private String nome;

	@Column
	private String descrizione;

	@Column(nullable = false)
	private BigDecimal prezzo;

	@OneToMany(mappedBy = "pizza")
	private Set<OrdinePizza> ordinePizza = new HashSet<>();

	@Builder
	public Pizza(Long id, String nome, String descrizione, BigDecimal prezzo) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}

}
