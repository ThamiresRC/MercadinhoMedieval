package com.fiap.mercadinhoMedieval.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mercadinhoMedieval.model.Item;
import com.fiap.mercadinhoMedieval.model.Personagem;
import com.fiap.mercadinhoMedieval.model.RaridadeItem;
import com.fiap.mercadinhoMedieval.model.TipoItem;
import com.fiap.mercadinhoMedieval.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repo;
    @Autowired
    private PersonagemService personagemService;

    public Item criar(Item item) {
        
        Personagem dono = personagemService.buscar(item.getDono().getId());
        item.setDono(dono);
        return repo.save(item);
    }

    public List<Item> listar() { return repo.findAll(); }

    public Item buscar(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));
    }

    public List<Item> listarPorPersonagem(Long personagemId) {
       
        personagemService.buscar(personagemId);
        return repo.findByDonoId(personagemId);
    }

    public Item atualizar(Long id, Item dto) {
        Item existente = buscar(id);
        existente.setNome(dto.getNome());
        existente.setTipo(dto.getTipo());
        existente.setRaridade(dto.getRaridade());
        existente.setPreco(dto.getPreco());
        
        if (dto.getDono() != null) {
            Personagem novoDono = personagemService.buscar(dto.getDono().getId());
            existente.setDono(novoDono);
        }
        return repo.save(existente);
    }

    public void deletar(Long id) { repo.delete(buscar(id)); }

    public List<Item> buscarPorNome(String nome) {
        return repo.findByNomeContainingIgnoreCase(nome);
}

    public List<Item> buscarPorTipo(TipoItem tipo) {
        return repo.findByTipo(tipo);
}

    public List<Item> buscarPorRaridade(RaridadeItem raridade) {
        return repo.findByRaridade(raridade);
}

    public List<Item> buscarPorPreco(BigDecimal min, BigDecimal max) {
        return repo.findByPrecoBetween(min, max);
}
}