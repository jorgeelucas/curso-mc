package com.nelioalves.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import com.nelioalves.cursomc.resources.util.StringUtils;
import com.nelioalves.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResources {
	
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> paginacao(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="directions", defaultValue="ASC") String directions
			){
		
		nome = StringUtils.decodeName(nome);
		List<Integer> ids = StringUtils.obterlistaDeIds(categorias);
		Page<Produto> produtoPaginado = produtoService.search(nome, ids, page, linesPerPage, orderBy, directions);
		Page<ProdutoDTO> produtoPaginadoDTO = produtoPaginado.map(paginado->new ProdutoDTO(paginado));
		return ResponseEntity.ok().body(produtoPaginadoDTO);
	}
	
}
