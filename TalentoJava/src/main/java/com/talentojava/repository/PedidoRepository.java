package com.talentojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentojava.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
