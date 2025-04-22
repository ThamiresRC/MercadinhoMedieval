package com.fiap.mercadinhoMedieval.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.mercadinhoMedieval.model.Item;
import com.fiap.mercadinhoMedieval.model.RaridadeItem;
import com.fiap.mercadinhoMedieval.model.TipoItem;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByDonoId(Long personagemId);
    List<Item> findByNomeContainingIgnoreCase(String nome);
    List<Item> findByTipo(TipoItem tipo);
    List<Item> findByRaridade(RaridadeItem raridade);
    List<Item> findByPrecoBetween(BigDecimal min, BigDecimal max);
}