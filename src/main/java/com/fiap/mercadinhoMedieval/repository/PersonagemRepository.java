package com.fiap.mercadinhoMedieval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.mercadinhoMedieval.model.ClassePersonagem;
import com.fiap.mercadinhoMedieval.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
    List<Personagem> findByClasse(ClassePersonagem classe);
}