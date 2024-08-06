package com.leonardo.awesomepizza.entity;

import java.util.HashSet;
import java.util.Set;

import com.leonardo.awesomepizza.entity.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ordini")
public class Ordine extends BaseEntity {

	@Column(nullable = false)
	private String statoOrdine;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Utente utente;
	
	 @OneToMany(mappedBy = "ordine")
	 private Set<OrdinePizza> ordinePizza = new HashSet<>();
	
	@Builder
    public Ordine(Long id, String statoOrdine, Utente utente) {
        this.id = id;
        this.statoOrdine = statoOrdine;
        this.utente = utente;
    }
}
