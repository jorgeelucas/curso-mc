package com.nelioalves.cursomc.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityViolation;
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
		Categoria categoriaGerenciada = obterPorId(categoria.getId());
		atualizarCategoria(categoriaGerenciada, categoria);
		return repo.save(categoriaGerenciada);
    }

	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e ) {
			throw new DataIntegrityViolation("impossível deletar categoria, existe produto associado");
		}
	}

	public List<Categoria> obterTodas() {
		return repo.findAll();
	}
	
	public Page<Categoria> obterTodasPaginada(Integer page, Integer linesPerPage, String orderBy, String directions) {
		PageRequest paginado = PageRequest.of(page, linesPerPage, Direction.valueOf(directions), orderBy);
		return repo.findAll(paginado);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
	
	private void atualizarCategoria(Categoria categoriaGerenciada, Categoria categoria) {
		categoriaGerenciada.setNome(categoria.getNome());
	}
}
