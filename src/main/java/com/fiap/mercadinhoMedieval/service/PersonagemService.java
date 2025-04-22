package com.fiap.mercadinhoMedieval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.mercadinhoMedieval.model.ClassePersonagem;
import com.fiap.mercadinhoMedieval.model.Personagem;
import com.fiap.mercadinhoMedieval.repository.PersonagemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonagemService {
    @Autowired
    private PersonagemRepository repo;

    public Personagem criar(Personagem p) 
    { return repo.save(p); 
        }


    public List<Personagem> listar()
    { return repo.findAll(); 
        }

    public Personagem buscar(Long id) 
    { return repo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado")); 
        }

    public Personagem atualizar(Long id, Personagem dto) {
        Personagem existente = buscar(id);

        existente.setNome(dto.getNome());
        existente.setClasse(dto.getClasse());
        existente.setNivel(dto.getNivel());
        existente.setMoedas(dto.getMoedas());
        return repo.save(existente);
    }
    public void deletar(Long id) { repo.delete(buscar(id)); }

    public List<Personagem> buscarPorNome(String nome) {
    return repo.findByNomeContainingIgnoreCase(nome);
    }

    public List<Personagem> buscarPorClasse(ClassePersonagem classe) {
    return repo.findByClasse(classe);
}
}