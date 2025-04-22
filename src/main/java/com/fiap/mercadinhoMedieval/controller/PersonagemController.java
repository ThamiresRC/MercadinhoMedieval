package com.fiap.mercadinhoMedieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.mercadinhoMedieval.model.ClassePersonagem;
import com.fiap.mercadinhoMedieval.model.Personagem;
import com.fiap.mercadinhoMedieval.service.PersonagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {
    @Autowired
    private PersonagemService service;

    @PostMapping
    public ResponseEntity<Personagem> criar(@Valid @RequestBody Personagem p) {
        Personagem criado = service.criar(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public List<Personagem> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Personagem buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("/{id}")
    public Personagem atualizar(@PathVariable Long id,
                               @Valid @RequestBody Personagem p) {
        return service.atualizar(id, p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Personagem> search(
    @RequestParam(value = "nome", required = false) String nome,
    @RequestParam(value = "classe", required = false) ClassePersonagem classe) {
    if (nome != null) return service.buscarPorNome(nome);
    if (classe != null) return service.buscarPorClasse(classe);
    return service.listar();
    
    }
}