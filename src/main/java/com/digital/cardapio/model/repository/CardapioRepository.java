package com.digital.cardapio.model.repository;

import com.digital.cardapio.model.entity.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<Cardapio, Integer> {
}
