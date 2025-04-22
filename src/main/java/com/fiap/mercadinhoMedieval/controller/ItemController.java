package com.fiap.mercadinhoMedieval.controller;

import java.math.BigDecimal;
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

import com.fiap.mercadinhoMedieval.model.Item;
import com.fiap.mercadinhoMedieval.model.RaridadeItem;
import com.fiap.mercadinhoMedieval.model.TipoItem;
import com.fiap.mercadinhoMedieval.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itens")
public class ItemController {
    @Autowired
    private ItemService service;

    @PostMapping
    public ResponseEntity<Item> criar(@Valid @RequestBody Item item) {
        Item criado = service.criar(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public List<Item> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Item buscar(@PathVariable Long id) { return service.buscar(id); }

    @GetMapping("/personagem/{personagemId}")
    public List<Item> listarPorPersonagem(@PathVariable Long personagemId) {
        return service.listarPorPersonagem(personagemId);
    }

    @PutMapping("/{id}")
    public Item atualizar(@PathVariable Long id,
                          @Valid @RequestBody Item item) {
        return service.atualizar(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Item> search(
    @RequestParam(value = "nome", required = false) String nome,
    @RequestParam(value = "tipo", required = false) TipoItem tipo,
    @RequestParam(value = "raridade", required = false) RaridadeItem raridade,
    @RequestParam(value = "precoMin", required = false) BigDecimal precoMin,
    @RequestParam(value = "precoMax", required = false) BigDecimal precoMax) {

    if (nome != null) return service.buscarPorNome(nome);
    if (tipo != null) return service.buscarPorTipo(tipo);
    if (raridade != null) return service.buscarPorRaridade(raridade);
    if (precoMin != null && precoMax != null) return service.buscarPorPreco(precoMin, precoMax);
    return service.listar();
    }
}