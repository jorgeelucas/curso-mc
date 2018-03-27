package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelioalves.cursomc.resources.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
