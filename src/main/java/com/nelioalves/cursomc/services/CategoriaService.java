package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria obterPorId(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException(Categoria.class.getName() + " de id: " + id + " não encontrado!"));
	}

	public Categoria cadastrar(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}

    public Categoria atualizar(Categoria categoria) {
		obterPorId(categoria.getId());
		return repo.save(categoria);
    }
}
