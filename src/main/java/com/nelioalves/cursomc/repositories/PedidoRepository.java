package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelioalves.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
